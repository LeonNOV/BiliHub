package com.leon.biuvideo.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;

import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityMainBinding;
import com.leon.biuvideo.ui.activities.actionActivities.DataListActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.FavoriteActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.HistoryActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.RelationActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.OrderActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.PopularActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.SettingActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.WatchLaterActivity;
import com.leon.biuvideo.ui.activities.publicActivities.DownloadActivity;
import com.leon.biuvideo.ui.activities.publicActivities.UserActivity;
import com.leon.biuvideo.utils.ViewUtils;

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

        /*HashMap<String, String> headers = new HashMap<>(1);
        headers.put("Cookie", HttpApi.DEFAULT_COOKIE);

        RefreshLoader<HomeRecommend, HomeRecommend.Data.Item> loader = new RefreshLoader<>(new RetrofitClient(BaseUrl.API, headers).getHttpApi().getHomeRecommend(),
                binding.home.data.getRoot());

        loader.init(new HomeRecommendAdapter(context));
        loader.setLayoutManager(new GridLayoutManager(context, 2));
        loader.setGuide(homeRecommend -> homeRecommend.getData().getItem());*/
    }

    /**
     * drawer function action
     */
    private void setDrawerFunctionListener() {
        binding.drawer.popular.setOnClickListener(v -> startPage(PopularActivity.class, null));
        binding.drawer.partition.setOnClickListener(v -> startPage(UserActivity.class, Map.of(UserActivity.PARAM, "38366371")));
        binding.drawer.orders.setOnClickListener(v -> startPage(OrderActivity.class, null));
        binding.drawer.favorites.setOnClickListener(v -> startPage(FavoriteActivity.class, null));
        binding.drawer.later.setOnClickListener(v -> startPage(WatchLaterActivity.class, null));
        binding.drawer.follows.setOnClickListener(v -> startPage(RelationActivity.class, null));
        binding.drawer.history.setOnClickListener(v -> startPage(HistoryActivity.class, null));
        binding.drawer.download.setOnClickListener(v -> startPage(DownloadActivity.class, null));
        binding.drawer.settings.setOnClickListener(v -> startPage(SettingActivity.class, null));
    }

    /**
     * start the DataListActivity
     */
    private void startPage(Class<? extends BaseActivity<? extends ViewBinding>> targetClass, Map<String, String> params) {
        if (params == null) {
            startActivity(targetClass);
        } else {
            startActivity(targetClass, params);
        }

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