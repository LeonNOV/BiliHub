package com.leon.bilihub.ui.activities.drawerFunction;

import android.widget.Toast;

import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.beans.account.WatchLater;
import com.leon.bilihub.databinding.ActivityWatchLaterBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.drawer.WatchLaterAdapter;
import com.leon.bilihub.ui.dialogs.LoginDialog;
import com.leon.bilihub.ui.widget.loader.RecyclerViewLoader;
import com.leon.bilihub.utils.PreferenceUtils;

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
        if (PreferenceUtils.getLoginStatus(context)) {
            binding.clear.setOnClickListener(v -> Toast.makeText(context, "开发中", Toast.LENGTH_SHORT).show());
            binding.remove.setOnClickListener(v -> Toast.makeText(context, "开发中", Toast.LENGTH_SHORT).show());

            RecyclerViewLoader<WatchLater, WatchLater.Data.WatchLaterData> loader = new RecyclerViewLoader<>(binding.content, new WatchLaterAdapter(context));
            loader
                    .setGuide(watchLater -> watchLater.getData().getList())
                    .setObservable(new RetrofitClient(BaseUrl.API, context).getHttpApi().getUserWatchLater())
                    .obtain(false);
        } else {
            new LoginDialog(context).show();
        }
    }
}