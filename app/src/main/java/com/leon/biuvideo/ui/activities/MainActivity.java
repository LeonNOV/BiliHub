package com.leon.biuvideo.ui.activities;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.account.AccountNav;
import com.leon.biuvideo.beans.home.AccountViewModel;
import com.leon.biuvideo.beans.home.HomeRecommend;
import com.leon.biuvideo.databinding.ActivityMainBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.DataStoreKey;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.http.TestValue;
import com.leon.biuvideo.ui.activities.drawerFunction.channel.ChannelActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.FavoriteActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.HistoryActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.RelationActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.OrderActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.PopularActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.SettingActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.WatchLaterActivity;
import com.leon.biuvideo.ui.activities.drawerFunction.partition.PartitionActivity;
import com.leon.biuvideo.ui.activities.publicActivities.DownloadActivity;
import com.leon.biuvideo.ui.activities.publicActivities.LiveStreamActivity;
import com.leon.biuvideo.ui.activities.publicActivities.VideoActivity;
import com.leon.biuvideo.ui.adapters.HomeRecommendAdapter;
import com.leon.biuvideo.ui.widget.loader.PaginationLoader;
import com.leon.biuvideo.utils.DataStoreUtils;
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
    public static AccountViewModel model;

    @Override
    public ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void init() {
        binding.home.userFace.setOnClickListener(v -> binding.getRoot().openDrawer(GravityCompat.START));
        binding.home.search.setOnClickListener(v -> startActivity(LiveStreamActivity.class, Map.of(LiveStreamActivity.PARAM, "83171")));
//        binding.home.search.setOnClickListener(v -> startActivity(LiveStreamActivity.class, Map.of(LiveStreamActivity.PARAM, "25198225")));
//        binding.home.search.setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_VIDEO,
//                VideoActivity.PARAM_ID, "BV1FV411y7oZ")));

//        binding.home.search.setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_PGC,
//                VideoActivity.PARAM_ID, "12044")));
//        binding.home.search.setOnClickListener(view -> startActivity(ArticleActivity.class, Map.of(ArticleActivity.PARAM, "18146926")));
//        binding.home.search.setOnClickListener(v -> startActivity(SearchActivity.class));
        binding.drawer.userContainer.setOnTouchListener((v, event) -> ViewUtils.zoom(event, binding.drawer.userContainer));
        binding.drawer.userContainer.setOnClickListener(v -> {
            startActivity(LoginActivity.class);
//            startActivity(ProfileActivity.class);
        });
        binding.drawer.userLogout.setOnClickListener(v -> localAccountStatus(null));

        setDrawerFunctionListener();

        // todo 开发中不启用
//        Boolean loginStatus = DataStoreUtils.INSTANCE.getData(DataStoreKey.LOGIN_STATUS, false);
//        if (loginStatus) {
//            refreshData();
//        } else {
//            initObserver();
//        }

//        HttpApi httpApi = new RetrofitClient(BaseUrl.API, Map.of(HttpApi.COOKIE, TestValue.TEST_COOKIE)).getHttpApi();
//        PaginationLoader<HomeRecommend, HomeRecommend.Data.Item> loader = new PaginationLoader<>(binding.home.data, new HomeRecommendAdapter(context), new GridLayoutManager(context, 2));
//        loader.enabledRefresh(true);
//        loader.setGuide(homeRecommend -> homeRecommend.getData().getItem());
//        loader.setUpdateInterface(loadType -> httpApi.getHomeRecommend());
//        loader.firstObtain();
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

    /**
     * 初始化观察者对象
     */
    private void initObserver() {
        model = new ViewModelProvider(this).get(AccountViewModel.class);
        model.getLoginStatus().observeForever(new Observer<>() {
            @Override
            public void onChanged(Boolean loginStatus) {
                if (loginStatus) {
                    refreshData();

                    model.getLoginStatus().removeObserver(this);
                    model = null;
                }

            }
        });
    }

    /**
     * 获取已登录用户数据
     */
    private void refreshData() {
        new ApiHelper<>(new RetrofitClient(BaseUrl.API,
                Map.of(HttpApi.COOKIE, DataStoreUtils.INSTANCE.getData(context, DataStoreKey.COOKIE, ""))).getHttpApi().getAccountInfo())
                .setOnResult(accountNav -> {
                    // 0：获取成功，即登陆成功
                    // -101：获取失败，即登录失败
                    if (accountNav.getCode() == 0) {
                        localAccountStatus(accountNav);
                    } else {
                        Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                }).doIt();
    }

    /**
     * 登录/退出 操作
     *
     * @param accountNav accountNav
     */
    private void localAccountStatus(AccountNav accountNav) {

        // 退出当前账户
        if (accountNav == null) {
            DataStoreUtils.INSTANCE.clearSync(context);
            binding.drawer.userContainer.setEnabled(true);
            binding.drawer.userFace.setImageResource(R.drawable.user_face_default);
            binding.home.userFace.setImageResource(R.drawable.user_face_default);
            binding.drawer.userName.setText("");
            binding.drawer.userLogout.setVisibility(View.GONE);

        // 登录成功
        } else {
            binding.drawer.userContainer.setEnabled(false);
            ViewUtils.setImg(context, binding.drawer.userFace, accountNav.getData().getFace());
            ViewUtils.setImg(context, binding.home.userFace, accountNav.getData().getFace());
            binding.drawer.userName.setText(accountNav.getData().getUname());
            binding.drawer.userLogout.setVisibility(View.VISIBLE);
        }
    }
}