package com.leon.biuvideo.ui.adapters.user;

import android.content.Context;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.user.UserArticle;
import com.leon.biuvideo.databinding.ItemUserArticleBinding;
import com.leon.biuvideo.ui.activities.publicActivities.ArticleActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

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
    public int getLayout(int viewType) {
        return R.layout.item_user_article;
    }

    @Override
    protected ItemUserArticleBinding getItemViewBinding() {
        return ItemUserArticleBinding.bind(itemView);
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
