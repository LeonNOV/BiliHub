package com.leon.bilihub.ui.adapters.searchResult;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.bilihub.beans.home.searchResult.SearchResultArticle;
import com.leon.bilihub.databinding.ItemUserArticleBinding;
import com.leon.bilihub.ui.activities.publicActivities.ArticleActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/06
 * @Desc
 */
public class SearchResultArticleAdapter extends BaseViewBindingAdapter<SearchResultArticle.Data.Result, ItemUserArticleBinding> {
    public SearchResultArticleAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemUserArticleBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemUserArticleBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_user_article, parent, false));
    }

    @Override
    protected void onBindViewHolder(SearchResultArticle.Data.Result data, ItemUserArticleBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(ArticleActivity.class, Map.of(ArticleActivity.PARAM, String.valueOf(data.getId()))));

        binding.title.setText(Html.fromHtml(data.getTitle()
                .replaceAll("<em class=\"keyword\">", "<font color=#fb7299>")
                .replaceAll("</em>", "</font>"), Html.FROM_HTML_MODE_COMPACT));
        ViewUtils.setImg(context, binding.cover, data.getImageUrls().get(0));
        binding.desc.setText(data.getDesc());
        binding.tag.setText(data.getCategoryName());
        binding.view.setText(ValueUtils.generateCN(data.getView()));
        binding.reply.setText(ValueUtils.generateCN(data.getReply()));
        binding.pubTime.setText(ValueUtils.generateTime(data.getPubTime(), "yyyy-MM-dd", true));
    }
}
