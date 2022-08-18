package com.leon.biuvideo.ui.adapters.searchResult;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.home.searchResult.SearchResultLive;
import com.leon.biuvideo.databinding.ItemSearchResultLiveBinding;
import com.leon.biuvideo.ui.activities.publicActivities.LiveStreamActivity;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/06
 * @Desc
 */
public class SearchResultLiveAdapter extends BaseViewBindingAdapter<SearchResultLive.Data.Result.LiveRoom, ItemSearchResultLiveBinding> {
    public SearchResultLiveAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemSearchResultLiveBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemSearchResultLiveBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_search_result_live, parent, false));
    }

    @Override
    protected void onBindViewHolder(SearchResultLive.Data.Result.LiveRoom data, ItemSearchResultLiveBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(LiveStreamActivity.class, Map.of(LiveStreamActivity.PARAM, String.valueOf(data.getRoomid()))));

        ViewUtils.setImg(context, binding.cover, data.getCover());
        ViewUtils.setImg(context, binding.playIcon, data.getWatchedShow().getIcon());
        if (data.getLiveStatus() == 1) {
            binding.status.setText("直播中");
            binding.status.setSelected(true);
        } else {
            binding.status.setText("未开播");
            binding.status.setSelected(false);
        }

        binding.play.setText(data.getWatchedShow().getTextSmall());
        binding.tag.setText(data.getCateName());
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getUname());
    }
}
