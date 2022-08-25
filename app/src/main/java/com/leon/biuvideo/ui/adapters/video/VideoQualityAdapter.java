package com.leon.biuvideo.ui.adapters.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoQuality;
import com.leon.biuvideo.databinding.ItemVideoQualityBinding;
import com.leon.biuvideo.model.VideoPlayerModel;
import com.leon.biuvideo.ui.widget.player.PlayerController;
import com.leon.biuvideo.utils.ViewUtils;
import com.leon.biuvideo.wraps.VideoQualityWrap;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class VideoQualityAdapter extends BaseViewBindingAdapter<VideoQuality, ItemVideoQualityBinding> {
    private PlayerController.OnSelectedListener<VideoQualityWrap> onSelectedListener;
    private int selectedPosition = 0;

    public VideoQualityAdapter(Context context) {
        super(context);

        VideoPlayerModel model = new ViewModelProvider(ViewUtils.scanForActivity(context)).get(VideoPlayerModel.class);
        List<VideoQuality> qualityList = model.getVideoQualityListDisplay().getValue();
        for (int i = 0; i < qualityList.size(); i++) {
            if (qualityList.get(i).getSelected()) {
                selectedPosition = i;
                break;
            }
        }
    }

    @Override
    protected ItemVideoQualityBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoQualityBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_quality, parent, false));
    }

    @Override
    protected void onBindViewHolder(VideoQuality data, ItemVideoQualityBinding binding, int position) {
        binding.getRoot().setOnClickListener(view -> {
            if (onSelectedListener != null && selectedPosition != position) {
                if (data.isOrdinary()) {
                    data.setSelected(true);

                    onSelectedListener.onSelected(new VideoQualityWrap(data.getQuality(), selectedPosition));
                    selectedPosition = position;

                    notifyItemChanged(position);
                } else {
                    // do something
                }
            }
        });

        binding.quality.setTextColor(data.getSelected() ? context.getColor(R.color.BiliBili_pink) : context.getColor(R.color.white));
        binding.quality.setText(data.getQualityStr());

        if (data.getExtra() != null) {
            binding.extra.setVisibility(View.VISIBLE);
            binding.extra.setText(data.getExtra());
        }
    }

    public void setOnSelectedListener(PlayerController.OnSelectedListener<VideoQualityWrap> onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}
