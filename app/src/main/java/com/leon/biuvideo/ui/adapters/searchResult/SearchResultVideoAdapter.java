package com.leon.biuvideo.ui.adapters.searchResult;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.home.searchResult.SearchResultVideo;
import com.leon.biuvideo.databinding.ItemVideoBinding;
import com.leon.biuvideo.ui.activities.publicActivities.VideoActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/02
 * @Desc
 */
public class SearchResultVideoAdapter extends BaseViewBindingAdapter<SearchResultVideo.Data.Result, ItemVideoBinding> {
    public SearchResultVideoAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemVideoBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video, parent, false));
    }

    @Override
    protected void onBindViewHolder(SearchResultVideo.Data.Result data, ItemVideoBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_BVID, data.getBvid())));

        ViewUtils.setImg(context, binding.cover, data.getPic());
        binding.extra.setText(data.getDuration());

        binding.title.setText(Html.fromHtml(data.getTitle()
                .replaceAll("<em class=\"keyword\">", "<font color=#fb7299>")
                .replaceAll("</em>", "</font>"), Html.FROM_HTML_MODE_COMPACT));

        binding.author.setText(data.getAuthor());
        binding.view.setText(ValueUtils.generateCN(data.getPlay()));
        binding.danmaku.setText(ValueUtils.generateCN(data.getDanmaku()));
    }
}
