package com.leon.biuvideo.ui.activities.drawerFunction;

import android.widget.Toast;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.account.WatchLater;
import com.leon.biuvideo.databinding.ActivityWatchLaterBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.drawerAdapters.WatchLaterAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/7/12
 * @Desc
 */
public class WatchLaterActivity extends BaseActivity<ActivityWatchLaterBinding> {
    @Override
    public ActivityWatchLaterBinding getViewBinding() {
        return ActivityWatchLaterBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        binding.clear.setOnClickListener(v -> Toast.makeText(context, "开发中", Toast.LENGTH_SHORT).show());
        binding.remove.setOnClickListener(v -> Toast.makeText(context, "开发中", Toast.LENGTH_SHORT).show());

        HttpApi httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();

        PaginationLoader<WatchLater, WatchLater.Data.WatchLaterData> loader = new PaginationLoader<>(binding.content, new WatchLaterAdapter(context));
        loader.setGuide(watchLater -> watchLater.getData().getList());
        loader.setObservable(httpApi.getUserWatchLater());
        loader.closeRefresh();
        loader.firstObtain();
    }
}