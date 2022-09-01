package com.leon.bilihub.ui.adapters.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.user.UserAudio;
import com.leon.bilihub.databinding.ItemUserAudioBinding;
import com.leon.bilihub.ui.activities.publicActivities.AudioActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/08
 * @Desc
 */
public class UserAudioAdapter extends ViewBindingAdapter<UserAudio.Data.Audio, ItemUserAudioBinding> {

    public UserAudioAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemUserAudioBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemUserAudioBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_user_audio, parent, false));
    }

    @Override
    protected void onBindViewHolder(UserAudio.Data.Audio audio, ItemUserAudioBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(AudioActivity.class, Map.of(AudioActivity.PARAM, String.valueOf(audio.getStatistic().getSid()))));

        binding.extra.setText(ValueUtils.toMediaDuration(audio.getDuration()));
        ViewUtils.setImg(context, binding.cover, audio.getCover());
        binding.view.setText(ValueUtils.generateCN(audio.getStatistic().getPlay()));
        binding.comment.setText(ValueUtils.generateCN(audio.getStatistic().getComment()));
        binding.title.setText(audio.getTitle());
        binding.pubTime.setText(ValueUtils.generateTime(audio.getPasstime(), "yyyy-MM-dd", true));
    }
}
