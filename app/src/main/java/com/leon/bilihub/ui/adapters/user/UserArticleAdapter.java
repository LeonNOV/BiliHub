package com.leon.bilihub.ui.adapters.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.user.UserArticle;
import com.leon.bilihub.databinding.ItemUserArticleBinding;
import com.leon.bilihub.ui.activities.publicActivities.ArticleActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/08
 * @Desc 用户界面
 */
public class UserArticleAdapter extends BaseViewBindingAdapter<UserArticle.Data.Article, ItemUserArticleBinding> {
    public UserArticleAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemUserArticleBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemUserArticleBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_user_article, parent, false));
    }

    @Override
    protected void onBindViewHolder(UserArticle.Data.Article data, ItemUserArticleBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(ArticleActivity.class, Map.of(ArticleActivity.PARAM, String.valueOf(data.getId()))));

        ViewUtils.setImg(context, binding.cover, data.getImageUrls().get(0));
        binding.title.setText(data.getTitle());
        binding.desc.setText(data.getSummary());
        binding.tag.setText(data.getCategory().getName());
        binding.view.setText(ValueUtils.generateCN(data.getStats().getView()));
        binding.reply.setText(ValueUtils.generateCN(data.getStats().getReply()));
        binding.pubTime.setText(ValueUtils.generateTime(data.getPublishTime(), "yyyy-MM-dd", true));
    }
}
