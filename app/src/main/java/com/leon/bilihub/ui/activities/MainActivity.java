package com.leon.bilihub.ui.activities;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.beans.account.AccountNav;
import com.leon.bilihub.beans.home.AccountViewModel;
import com.leon.bilihub.beans.home.HomeRecommendApp;
import com.leon.bilihub.databinding.ActivityMainBinding;
import com.leon.bilihub.http.ApiHelper;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.activities.drawerFunction.channel.ChannelActivity;
import com.leon.bilihub.ui.activities.drawerFunction.FavoriteActivity;
import com.leon.bilihub.ui.activities.drawerFunction.HistoryActivity;
import com.leon.bilihub.ui.activities.drawerFunction.RelationActivity;
import com.leon.bilihub.ui.activities.drawerFunction.OrderActivity;
import com.leon.bilihub.ui.activities.drawerFunction.PopularActivity;
import com.leon.bilihub.ui.activities.drawerFunction.SettingActivity;
import com.leon.bilihub.ui.activities.drawerFunction.WatchLaterActivity;
import com.leon.bilihub.ui.activities.drawerFunction.partition.PartitionActivity;
import com.leon.bilihub.ui.activities.publicActivities.DownloadActivity;
import com.leon.bilihub.ui.activities.search.SearchActivity;
import com.leon.bilihub.ui.adapters.HomeRecommendAdapter;
import com.leon.bilihub.ui.dialogs.TipDialog;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;
import com.leon.bilihub.utils.DataStoreUtils;
import com.leon.bilihub.utils.PreferenceUtils;
import com.leon.bilihub.utils.ViewUtils;

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
    private HttpApi recommendHttpApi;

    @Override
    public ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void init() {
        closeImmersion();

        binding.home.userFace.setOnClickListener(v -> binding.getRoot().openDrawer(GravityCompat.START));
        binding.home.search.setOnClickListener(v -> startActivity(SearchActivity.class));
        binding.drawer.userContainer.setOnTouchListener((v, event) -> ViewUtils.zoom(event, binding.drawer.userContainer));
        binding.drawer.userContainer.setOnClickListener(v -> {
            startActivity(LoginActivity.class);
//            startActivity(ProfileActivity.class);
        });
        binding.drawer.userLogout.setOnClickListener(v -> {
            TipDialog tipDialog = new TipDialog(context);
            tipDialog.setTitle("退出");
            tipDialog.setContent("是否要退出当前账户？");
            tipDialog.setActionStr("取消", "退出");
            tipDialog.setOnActionListener(action -> {
                if (action) {
                    localAccountStatus(null);
                }

                tipDialog.dismiss();
            });
            tipDialog.show();
        });

        setDrawerFunctionListener();

        recommendHttpApi = new RetrofitClient(BaseUrl.APP).getHttpApi();

        boolean loginStatus = PreferenceUtils.getLoginStatus(context);
        if (loginStatus) {
            refreshData();
        } else {
            initObserver();
        }

        PaginationLoader<HomeRecommendApp, HomeRecommendApp.Data.Item> loader;
        int recommendStyle = PreferenceUtils.getRecommendStyle(context);
        if (recommendStyle == 1) {
            loader = new PaginationLoader<>(binding.home.data, new HomeRecommendAdapter(context));
        } else {
            loader = new PaginationLoader<>(binding.home.data, new HomeRecommendAdapter(context), new GridLayoutManager(context, 2));
        }
        loader.enabledRefresh(true);
        loader.setGuide(homeRecommend -> homeRecommend.getData().getItems());
        loader.setUpdateInterface(loadType -> recommendHttpApi.getHomeRecommendApp());
        loader.firstObtain();
    }

    /**
     * drawer function action
     */
    private void setDrawerFunctionListener() {
        binding.drawer.popular.setOnClickListener(v -> startPage(PopularActivity.class));
        binding.drawer.partition.setOnClickListener(v -> startPage(PartitionActivity.class));
        binding.drawer.channel.setOnClickListener(v -> startPage(ChannelActivity.class));
        binding.drawer.orders.setOnClickListener(v -> startPage(OrderActivity.class));
        binding.drawer.favorites.setOnClickListener(v -> startPage(FavoriteActivity.class));
        binding.drawer.later.setOnClickListener(v -> startPage(WatchLaterActivity.class));
        binding.drawer.follows.setOnClickListener(v -> startPage(RelationActivity.class));
        binding.drawer.history.setOnClickListener(v -> startPage(HistoryActivity.class));
        binding.drawer.download.setOnClickListener(v -> startPage(DownloadActivity.class));
        binding.drawer.settings.setOnClickListener(v -> startPage(SettingActivity.class));
    }

    /**
     * start the DataListActivity
     */
    private void startPage(Class<? extends BaseActivity<? extends ViewBinding>> targetClass) {
        startActivity(targetClass);
        delayCloseDrawer();
    }

    /**
     * delay closing the drawer
     */
    private void delayCloseDrawer() {
        if (scheduledThreadPoolExecutor == null) {
            scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, r -> new Thread(r, "delayCloseDrawer"));
        }

        scheduledThreadPoolExecutor.schedule(() -> binding.getRoot().closeDrawer(GravityCompat.START), 800, TimeUnit.MILLISECONDS);
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
        new ApiHelper<>(new RetrofitClient(BaseUrl.API, context).getHttpApi().getAccountInfo())
                .setOnResult(accountNav -> {
                    // 0：获取成功，即登陆成功
                    // -101：获取失败，即登录失败
                    if (accountNav.getCode() == 0) {
                        recommendHttpApi = new RetrofitClient(BaseUrl.APP, context).getHttpApi();
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
            PreferenceUtils.setVipStatus(context, accountNav.getData().getVipStatus() != 0);
            binding.drawer.userContainer.setEnabled(false);
            ViewUtils.setImg(context, binding.drawer.userFace, accountNav.getData().getFace());
            ViewUtils.setImg(context, binding.home.userFace, accountNav.getData().getFace());
            binding.drawer.userName.setText(accountNav.getData().getUname());
            binding.drawer.userLogout.setVisibility(View.VISIBLE);
        }
    }
}