package com.leon.biuvideo.ui.adapters.drawer.relation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingMultipleAdapter;
import com.leon.biuvideo.beans.home.drawerFunction.popular.PopularData;
import com.leon.biuvideo.databinding.ItemRankPgcBinding;
import com.leon.biuvideo.databinding.ItemVideoBinding;
import com.leon.biuvideo.ui.activities.publicActivities.VideoActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/06
 * @Desc
 */
public class PopularRankAdapter extends BaseViewBindingMultipleAdapter<PopularData> {
    public final static int ITEM_VIDEO = R.layout.item_video;
    public final static int ITEM_RANK_PGC = R.layout.item_rank_pgc;

    private int layoutId = ITEM_VIDEO;

    public PopularRankAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemViewType(PopularData data) {
        return layoutId;
    }

    @Override
    protected ViewBinding getItemViewBinding(Context context, ViewGroup parent, int viewType) {
        ViewBinding binding;

        if (viewType == ITEM_VIDEO) {
            binding = ItemVideoBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video, parent, false));
        } else {
            binding = ItemRankPgcBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_rank_pgc, parent, false));
        }
        return binding;
    }

    @Override
    protected void onBindViewHolder(PopularData data, ViewBinding binding, int position) {
        if (binding instanceof ItemVideoBinding) {
            initItemVideo((ItemVideoBinding) binding, data);
        } else if (binding instanceof ItemRankPgcBinding){
            initItemRankPgc((ItemRankPgcBinding) binding, data);
        }
    }

    private void initItemVideo(ItemVideoBinding binding, PopularData data) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, String.valueOf(VideoActivity.TYPE_VIDEO),
                VideoActivity.PARAM_ID, data.getBvid())));

        ViewUtils.setImg(context, binding.cover, data.getCover());
        binding.extra.setText(ValueUtils.toMediaDuration(data.getDuration()));
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getAuthor());
        binding.view.setText(ValueUtils.generateCN(data.getPlay()));
        binding.danmaku.setText(ValueUtils.generateCN(data.getDanmaku()));
    }

    private void initItemRankPgc(ItemRankPgcBinding binding, PopularData data) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, String.valueOf(VideoActivity.TYPE_FT),
                VideoActivity.PARAM_SEASON_ID, data.getBvid())));

        ViewUtils.setImg(context, binding.cover, data.getCover());
        binding.title.setText(data.getTitle());
        binding.extra.setText(data.getAuthor());
        binding.view.setText(ValueUtils.generateCN(data.getPlay()));
        binding.danmaku.setText(ValueUtils.generateCN(data.getDanmaku()));
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
}
