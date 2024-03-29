package com.leon.bilihub.ui.activities.drawerFunction.channel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Toast;

import androidx.core.graphics.ColorKt;
import androidx.fragment.app.Fragment;

import com.leon.bilihub.base.baseActivity.AsyncHttpActivity;
import com.leon.bilihub.beans.home.channel.ChannelDetail;
import com.leon.bilihub.databinding.ActivityChannelDetailBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.RequestData;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.fragments.channelFragments.ChannelFeaturedFragment;
import com.leon.bilihub.ui.fragments.channelFragments.ChannelMultipleFragment;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.core.Observable;

/**
 * @Author Leon
 * @Time 2022/7/15
 * @Desc
 */
public class ChannelDetailActivity extends AsyncHttpActivity<ActivityChannelDetailBinding, ChannelDetail> {
    public static final String PARAM_ID = "id";
    public String channelId;

    @Override
    public ActivityChannelDetailBinding getViewBinding() {
        return ActivityChannelDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        this.channelId = params.getString(PARAM_ID);
        binding.back.setOnClickListener(v -> backPressed());

        binding.appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                crossFade(true);
            } else {
                if (binding.toolBarName.getVisibility() != View.GONE) {
                    crossFade(false);
                }
            }
        });
    }

    @Override
    protected RequestData setRequestData() {
        return new RequestData(BaseUrl.API);
    }

    @Override
    protected Observable<ChannelDetail> createObservable(RetrofitClient retrofitClient) {
        return retrofitClient.getHttpApi().getChannelDetail(channelId);
    }

    @Override
    protected void onAsyncResult(ChannelDetail channelDetail) {
        ViewUtils.setImg(context, binding.bg, channelDetail.getData().getBackground());
        ViewUtils.setImg(context, binding.cover, channelDetail.getData().getCover());

        ColorDrawable drawable = new ColorDrawable(ColorKt.toColorInt(channelDetail.getData().getThemeColor()));
        drawable.setAlpha(channelDetail.getData().getAlpha());
        binding.bg.setForeground(drawable);

        binding.subscribe.setOnClickListener(v -> Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show());
        binding.subscribe.setTextColor(ColorKt.toColorInt(channelDetail.getData().getThemeColor()));
        binding.name.setText(channelDetail.getData().getName());
        binding.toolBarName.setText(channelDetail.getData().getName());
        binding.count.setText(String.format(Locale.CHINESE, "%s视频", channelDetail.getData().getArchiveCount()));
        binding.play.setText(String.format(Locale.CHINESE, "%s播放", channelDetail.getData().getViewCount()));
        binding.featured.setText(String.format(Locale.CHINESE, "%s个精选视频", ValueUtils.generateCN(channelDetail.getData().getFeaturedCount())));

        List<Fragment> fragments = new ArrayList<>(2);
        for (ChannelDetail.Data.Tab tab : channelDetail.getData().getTabs()) {
            if ("featured".equals(tab.getType())) {
                fragments.add(new ChannelFeaturedFragment(channelId, tab.getOptions()));
            } else {
                fragments.add(new ChannelMultipleFragment(channelId));
            }
        }

        ViewUtils.initTabLayout(this, binding.content.tabLayout, binding.content.viewPager, fragments, "精选", "综合");
    }

    private void crossFade(boolean isShow) {
        binding.toolBarName.animate()
                .alpha(isShow ? 1f : 0f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        binding.toolBarName.setVisibility(isShow ? View.VISIBLE : View.GONE);
                    }
                });
    }
}