package com.leon.bilihub.ui.adapters.video.live;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.resources.live.LiveStream;
import com.leon.bilihub.databinding.ItemLiveRoadBinding;
import com.leon.bilihub.model.LivePlayerModel;
import com.leon.bilihub.ui.widget.player.PlayerController;
import com.leon.bilihub.utils.ViewUtils;
import com.leon.bilihub.wraps.LiveQualityWrap;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/08/25
 * @Desc
 */
public class LiveQualityAdapter extends ViewBindingAdapter<LiveStream.Data.QualityDescription, ItemLiveRoadBinding> {
    private PlayerController.OnSelectedListener<LiveQualityWrap> onSelectedListener;
    private int selectedPosition = 0;

    public LiveQualityAdapter(Context context) {
        super(context);

        LivePlayerModel livePlayerModel = new ViewModelProvider(ViewUtils.scanForActivity(context)).get(LivePlayerModel.class);
        List<LiveStream.Data.QualityDescription> qualityDescriptionList = livePlayerModel.getLiveQualityList().getValue();
        for (int i = 0; i < qualityDescriptionList.size(); i++) {
            if (qualityDescriptionList.get(0).getSelected()) {
                selectedPosition = i;
                break;
            }
        }
    }

    @Override
    protected ItemLiveRoadBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemLiveRoadBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_live_road, parent, false));
    }

    @Override
    protected void onBindViewHolder(LiveStream.Data.QualityDescription data, ItemLiveRoadBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> {
            if (onSelectedListener != null && selectedPosition != position) {
                data.setSelected(true);

                onSelectedListener.onSelected(new LiveQualityWrap(data.getQn(), selectedPosition));
                selectedPosition = position;

                notifyItemChanged(position);
            }
        });

        binding.road.setText(data.getDesc());
        if (data.getSelected()) {
            binding.road.setTextColor(context.getColor(R.color.pink));
        } else {
            binding.road.setTextColor(context.getColor(R.color.white));
        }
    }

    public void setOnSelectedListener(PlayerController.OnSelectedListener<LiveQualityWrap> onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}
