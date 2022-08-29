package com.leon.bilihub.ui.adapters.searchResult;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.bilihub.beans.home.searchResult.SearchResultLive;
import com.leon.bilihub.databinding.ItemSearchResultLiveBinding;
import com.leon.bilihub.ui.activities.publicActivities.LiveStreamActivity;
import com.leon.bilihub.utils.ViewUtils;

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
        binding.title.setText(Html.fromHtml(data.getTitle().replaceAll("<em class=\"keyword\">", "<font color=#fb7299>").replaceAll("</em>", "</font>"), Html.FROM_HTML_MODE_COMPACT));
        binding.author.setText(data.getUname());
    }
}
