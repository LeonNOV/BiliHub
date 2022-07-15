package com.leon.biuvideo.ui.activities.drawerFunction.channel;

import androidx.fragment.app.Fragment;

import com.leon.biuvideo.base.baseActivity.AsyncHttpActivity;
import com.leon.biuvideo.beans.home.channel.Channel;
import com.leon.biuvideo.databinding.ActivityChannelBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.RequestData;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.fragments.channelFragments.ChannelFragment;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * @Author Leon
 * @Time 2022/7/15
 * @Desc
 */
public class ChannelActivity extends AsyncHttpActivity<ActivityChannelBinding, Channel> {

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
    protected Observable<Channel> createObservable(RetrofitClient retrofitClient) {
        return retrofitClient.getHttpApi().getChannel();
    }

    @Override
    protected void async(ApiHelper<Channel> apiHelper) {
        apiHelper.setOnResult(channel -> {
            channel.getData().getCategories().add(0, new Channel.Data.Category("0", -1, "我的订阅"));

            int count = channel.getData().getCategories().size();
            List<Fragment> fragments = new ArrayList<>(count);
            String[] titles = new String[count];

            for (int i = 0; i < channel.getData().getCategories().size(); i++) {
                Channel.Data.Category category = channel.getData().getCategories().get(i);

                fragments.add(new ChannelFragment(category));
                titles[i] = category.getName();
            }

            ViewUtils.initTabLayout(ChannelActivity.this, binding.content.tabLayout, binding.content.viewPager, fragments, titles);
        }).doIt();
    }
}