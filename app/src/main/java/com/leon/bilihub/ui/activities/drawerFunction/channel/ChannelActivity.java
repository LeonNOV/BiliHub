package com.leon.bilihub.ui.activities.drawerFunction.channel;

import androidx.fragment.app.Fragment;

import com.leon.bilihub.base.baseActivity.AsyncHttpActivity;
import com.leon.bilihub.beans.home.channel.ChannelCategory;
import com.leon.bilihub.databinding.ActivityChannelBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.RequestData;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.fragments.channelFragments.ChannelFragment;
import com.leon.bilihub.utils.PreferenceUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * @Author Leon
 * @Time 2022/7/15
 * @Desc
 */
public class ChannelActivity extends AsyncHttpActivity<ActivityChannelBinding, ChannelCategory> {

    @Override
    public ActivityChannelBinding getViewBinding() {
        return ActivityChannelBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {}

    @Override
    protected RequestData setRequestData() {
        return new RequestData(BaseUrl.API);
    }

    @Override
    protected Observable<ChannelCategory> createObservable(RetrofitClient retrofitClient) {
        return retrofitClient.getHttpApi().getChannelCategory();
    }

    @Override
    protected void onAsyncResult(ChannelCategory channelCategory) {
        if (PreferenceUtils.getLoginStatus(context)) {
            channelCategory.getData().getCategories().add(0, new ChannelCategory.Data.Category("0", -1, "我的订阅"));
        }

        int count = channelCategory.getData().getCategories().size();
        List<Fragment> fragments = new ArrayList<>(count);
        String[] titles = new String[count];

        for (int i = 0; i < channelCategory.getData().getCategories().size(); i++) {
            ChannelCategory.Data.Category category = channelCategory.getData().getCategories().get(i);

            fragments.add(new ChannelFragment(category));
            titles[i] = category.getName();
        }

        ViewUtils.initTabLayout(ChannelActivity.this, binding.content.tabLayout, binding.content.viewPager, fragments, titles);
    }
}