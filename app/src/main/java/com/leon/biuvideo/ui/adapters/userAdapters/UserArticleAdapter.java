package com.leon.biuvideo.ui.adapters.userAdapters;

import android.content.Context;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.user.UserArticle;
import com.leon.biuvideo.databinding.UserArticleItemBinding;
import com.leon.biuvideo.ui.activities.publicActivities.ArticleActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

/**
 * @Author Leon
 * @Time 2022/07/08
 * @Desc
 */
public class UserArticleAdapter extends BaseViewBindingAdapter<UserArticle.Data.Article, UserArticleItemBinding> {
    public UserArticleAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayout(int viewType) {
        return R.layout.item_user_article;
    }

    @Override
    protected UserArticleItemBinding getItemViewBinding() {
        return UserArticleItemBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(UserArticle.Data.Article data, UserArticleItemBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> ActivityManager.startActivity(context, ArticleActivity.class));

        binding.title.setText(data.getTitle());
        ViewUtils.setImg(context, binding.cover, data.getImageUrls().get(0));
        binding.desc.setText(data.getSummary());
        binding.tag.setText(data.getCategory().getName());
        binding.view.setText(ValueUtils.generateCN(data.getStats().getView()));
        binding.reply.setText(ValueUtils.generateCN(data.getStats().getReply()));
        binding.pubTime.setText(ValueUtils.generateTime(data.getPublishTime(), "yyyy-MM-dd", true));
    }
}
