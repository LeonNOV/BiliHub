package com.leon.bilihub.ui.adapters.searchResult;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.home.searchResult.SearchResultMedia;
import com.leon.bilihub.databinding.ItemSearchResultFtBinding;
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
public class SearchResultPgcAdapter extends ViewBindingAdapter<SearchResultMedia.Data.Result, ItemSearchResultFtBinding> {

    public SearchResultPgcAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemSearchResultFtBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemSearchResultFtBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_search_result_ft, parent, false));
    }

    @Override
    protected void onBindViewHolder(SearchResultMedia.Data.Result data, ItemSearchResultFtBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_PGC,
                VideoActivity.PARAM_ID, data.getSeasonId())));

        ViewUtils.setImg(context, binding.cover, data.getCover());
        if (data.getBadges() != null && !data.getBadges().isEmpty()) {
            binding.badge.setVisibility(View.VISIBLE);
            binding.badge.setText(data.getBadges().get(0).getText());
        }

        binding.seasonType.setText(data.getSeasonTypeName());
        binding.title.setText(ValueUtils.keywordTrim(data.getTitle()));

        StringBuilder extra = new StringBuilder();
        if (!"".equals(data.getStyles())) {
            extra.append(data.getStyles()).append(" | ");
        }
        extra
                .append(ValueUtils.generateTime(data.getPubtime(), "yyyy", true))
                .append(" | ")
                .append(data.getAreas());
        binding.extra.setText(extra);

        if (!"".equals(data.getCv())) {
            binding.cv.setVisibility(View.VISIBLE);
            binding.cv.setText(String.format(Locale.CHINESE, "出演：%s", data.getCv().replaceAll("\n", " ")));
        } else {
            binding.staff.setVisibility(View.VISIBLE);
            binding.staff.setText(String.format(Locale.CHINESE, "制作团队：%s", data.getStaff().replaceAll("\n", " ")));
        }
        binding.desc.setText(String.format(Locale.CHINESE, "简介：%s", data.getDesc()));

        if (data.getMediaScore().getUserCount() != 0) {
            binding.ratingContainer.setVisibility(View.VISIBLE);
            binding.ratingScore.setText(String.valueOf(data.getMediaScore().getScore()));
            binding.ratingCount.setText(String.format(Locale.CHINESE, "%s人评分", ValueUtils.generateCN(data.getMediaScore().getUserCount())));
        }
    }
}
