package com.leon.biuvideo.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.leon.biuvideo.actions.drawerActions.DownloadAction;
import com.leon.biuvideo.actions.drawerActions.FavoriteAction;
import com.leon.biuvideo.actions.drawerActions.FollowsAction;
import com.leon.biuvideo.actions.drawerActions.HistoryAction;
import com.leon.biuvideo.actions.drawerActions.OrderAction;
import com.leon.biuvideo.actions.drawerActions.WatchLaterAction;
import com.leon.biuvideo.base.baseAction.BaseAction;
import com.leon.biuvideo.actions.drawerActions.PopularAction;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.home.HomeRecommend;
import com.leon.biuvideo.databinding.ActivityMainBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.activities.actionActivities.DataListActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.PartitionActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.PopularActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.SettingActivity;
import com.leon.biuvideo.ui.activities.publicActivities.UserActivity;
import com.leon.biuvideo.ui.adapters.HomeRecommendAdapter;
import com.leon.biuvideo.utils.RefreshLoader;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Leon
 * @Time 2021/10/30
 * @Desc
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    @Override
    public ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void init() {
        binding.home.userFace.setOnClickListener(v -> binding.getRoot().openDrawer(GravityCompat.START));
        binding.home.search.setOnClickListener(v -> startActivity(SearchActivity.class));
        binding.drawer.userLogout.setOnClickListener(v -> {
            Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show();
        });
        binding.drawer.userContainer.setOnTouchListener((v, event) -> ViewUtils.Zoom(event, binding.drawer.userContainer));
        binding.drawer.userContainer.setOnClickListener(v -> {
            boolean isLogin = false;

            if (isLogin) {
                startActivity(UserActivity.class);
            } else {
                startActivity(LoginActivity.class);
            }
        });

        setDrawerFunctionListener();

//        HashMap<String, String> headers = new HashMap<>(1);
//        headers.put("Cookie", HttpApi.DEFAULT_COOKIE);
//
//        RefreshLoader<HomeRecommend, HomeRecommend.Data.Item> loader = new RefreshLoader<>(new RetrofitClient(BaseUrl.API, headers).getHttpApi().getHomeRecommend(),
//                binding.home.data.getRoot());
//
//        loader.init(new HomeRecommendAdapter(context));
//        loader.setLayoutManager(new GridLayoutManager(context, 2));
//        loader.setGuide(homeRecommend -> homeRecommend.getData().getItem());
    }

    /**
     * drawer function action
     */
    private void setDrawerFunctionListener() {
        binding.drawer.popular.setOnClickListener(v -> startActivity(PopularActivity.class));
//        binding.drawer.popular.setOnClickListener(v -> functionGo(PopularAction.class));
        binding.drawer.partition.setOnClickListener(v -> {
            startActivity(UserActivity.class, Map.of(UserActivity.PARAM, "212535360"));
            delayCloseDrawer();
        });
        binding.drawer.orders.setOnClickListener(v -> functionGo(OrderAction.class));
        binding.drawer.favorites.setOnClickListener(v -> functionGo(FavoriteAction.class));
        binding.drawer.later.setOnClickListener(v -> functionGo(WatchLaterAction.class));
        binding.drawer.follows.setOnClickListener(v -> functionGo(FollowsAction.class));
        binding.drawer.history.setOnClickListener(v -> functionGo(HistoryAction.class));
        binding.drawer.download.setOnClickListener(v -> functionGo(DownloadAction.class));
        binding.drawer.settings.setOnClickListener(v -> {
            startActivity(SettingActivity.class);
            delayCloseDrawer();
        });
    }

    /**
     * start the DataListActivity
     */
    private void functionGo(Class<? extends BaseAction> targetClass) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseAction.ACTION, targetClass.getName());
        ActivityManager.startActivity(context, DataListActivity.class, bundle);

        delayCloseDrawer();
    }

    /**
     * delay closing the drawer
     */
    private void delayCloseDrawer() {
        if (scheduledThreadPoolExecutor == null) {
            scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, r -> new Thread(r, "delayCloseDrawer"));
        }

        scheduledThreadPoolExecutor.schedule(() -> {
            binding.getRoot().closeDrawer(GravityCompat.START);
        }, 800, TimeUnit.MILLISECONDS);
    }
}