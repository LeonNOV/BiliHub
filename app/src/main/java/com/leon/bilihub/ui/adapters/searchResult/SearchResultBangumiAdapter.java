package com.leon.bilihub.ui.adapters.searchResult;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.home.searchResult.SearchResultMedia;
import com.leon.bilihub.databinding.ItemSearchResultBangumiBinding;
import com.leon.bilihub.ui.activities.publicActivities.VideoActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Locale;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/02
 * @Desc
 */
public class SearchResultBangumiAdapter extends ViewBindingAdapter<SearchResultMedia.Data.Result, ItemSearchResultBangumiBinding> {
    private boolean proxy = false;
    public SearchResultBangumiAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemSearchResultBangumiBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemSearchResultBangumiBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_search_result_bangumi, parent, false));
    }

    @Override
    protected void onBindViewHolder(SearchResultMedia.Data.Result data, ItemSearchResultBangumiBinding binding, int position) {
        Bundle bundle = new Bundle();
        bundle.putString(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_PGC);
        bundle.putString(VideoActivity.PARAM_ID, data.getSeasonId());
        bundle.putBoolean(VideoActivity.PARAM_PROXY, proxy);

        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, bundle));

        ViewUtils.setImg(context, binding.cover, data.getCover());
        if (data.getBadges() != null && !data.getBadges().isEmpty()) {
            SearchResultMedia.Data.Result.Badge badge = data.getBadges().get(0);
            binding.badge.setText(badge.getText());
        }

        binding.title.setText(ValueUtils.keywordTrim(data.getTitle()));
        StringBuilder extra = new StringBuilder();
        extra
                .append(ValueUtils.generateTime(data.getPubtime(), "yyyy", true)).append(" | ")
                .append(data.getSeasonTypeName()).append(" | ")
                .append(data.getAreas());
        binding.extra.setText(extra);

        binding.tag.setText(data.getStyles());
        binding.desc.setText(String.format(Locale.CHINESE, "简介：%s", data.getDesc()));
        if (data.getMediaScore().getUserCount() != 0) {
            binding.ratingContainer.setVisibility(View.VISIBLE);
            binding.ratingScore.setText(String.valueOf(data.getMediaScore().getScore()));
            binding.ratingCount.setText(String.format(Locale.CHINESE, "%s人评分", ValueUtils.generateCN(data.getMediaScore().getUserCount())));
        }
    }

    public void useProxy() {
        this.proxy = true;
    }
}
