package com.leon.biuvideo.ui.adapters.user;

import android.content.Context;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.user.UserAudio;
import com.leon.biuvideo.databinding.ItemUserAudioBinding;
import com.leon.biuvideo.ui.activities.publicActivities.AudioActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/08
 * @Desc
 */
public class UserAudioAdapter extends BaseViewBindingAdapter<UserAudio.Data.Audio, ItemUserAudioBinding> {

    public UserAudioAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayout(int viewType) {
        return R.layout.item_user_audio;
    }

    @Override
    protected ItemUserAudioBinding getItemViewBinding() {
        return ItemUserAudioBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(UserAudio.Data.Audio audio, ItemUserAudioBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(AudioActivity.class, Map.of(AudioActivity.PARAM, String.valueOf(audio.getStatistic().getSid()))));

        binding.extra.setText(ValueUtils.lengthGenerate(audio.getDuration()));
        ViewUtils.setImg(context, binding.cover, audio.getCover());
        binding.view.setText(ValueUtils.generateCN(audio.getStatistic().getPlay()));
        binding.comment.setText(ValueUtils.generateCN(audio.getStatistic().getComment()));
        binding.title.setText(audio.getTitle());
        binding.pubTime.setText(ValueUtils.generateTime(audio.getPasstime(), "yyyy-MM-dd", true));
    }
}
