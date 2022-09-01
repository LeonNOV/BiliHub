package com.leon.bilihub.ui.adapters.searchResult;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.home.searchResult.SearchResultVideo;
import com.leon.bilihub.databinding.ItemVideoBinding;
import com.leon.bilihub.ui.activities.publicActivities.VideoActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/02
 * @Desc
 */
public class SearchResultVideoAdapter extends ViewBindingAdapter<SearchResultVideo.Data.Result, ItemVideoBinding> {
    public SearchResultVideoAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemVideoBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video, parent, false));
    }

    @Override
    protected void onBindViewHolder(SearchResultVideo.Data.Result data, ItemVideoBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_VIDEO,
                VideoActivity.PARAM_ID, data.getBvid())));

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
