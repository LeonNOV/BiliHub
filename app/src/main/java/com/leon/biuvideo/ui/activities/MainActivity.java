package com.leon.biuvideo.ui.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.viewbinding.ViewBinding;

import com.google.gson.Gson;
import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.home.drawerFunction.Series;
import com.leon.biuvideo.databinding.ActivityMainBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.activities.drawerFunction.channel.ChannelActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.FavoriteActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.HistoryActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.RelationActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.OrderActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.PopularActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.SettingActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.WatchLaterActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.partition.PartitionActivity;
import com.leon.biuvideo.ui.activities.publicActivities.ArticleActivity;
import com.leon.biuvideo.ui.activities.publicActivities.AudioActivity;
import com.leon.biuvideo.ui.activities.publicActivities.DownloadActivity;
import com.leon.biuvideo.ui.activities.publicActivities.PictureActivity;
import com.leon.biuvideo.ui.activities.publicActivities.UserActivity;
import com.leon.biuvideo.ui.activities.search.SearchActivity;
import com.leon.biuvideo.ui.fragments.popularFragments.WeeklyFragment;
import com.leon.biuvideo.utils.ViewUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        binding.home.search.setOnClickListener(v -> startActivity(PictureActivity.class, Map.of(PictureActivity.PARAM, "656073292606603282")));
//        binding.home.search.setOnClickListener(v -> startActivity(SearchActivity.class));
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

        /*HttpApi httpApi = new RetrofitClient(BaseUrl.API, Map.of(HttpApi.Cookie, HttpApi.DEFAULT_COOKIE)).getHttpApi();
        PaginationLoader<HomeRecommend, HomeRecommend.Data.Item> loader = new PaginationLoader<>(binding.home.data, new HomeRecommendAdapter(context));

        loader.setLayoutManager(new GridLayoutManager(context, 2));
        loader.closeRefresh();
        loader.setGuide(homeRecommend -> homeRecommend.getData().getItem());
        loader.setObservable(httpApi.getHomeRecommend());
        loader.firstObtain();*/
    }

    /**
     * drawer function action
     */
    private void setDrawerFunctionListener() {
        binding.drawer.popular.setOnClickListener(v -> startPage(PopularActivity.class, null));
        binding.drawer.partition.setOnClickListener(v -> startPage(PartitionActivity.class, null));
        binding.drawer.channel.setOnClickListener(v -> startPage(ChannelActivity.class, null));
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