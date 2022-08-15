package com.leon.biuvideo.ui.adapters.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.account.History;
import com.leon.biuvideo.databinding.ItemHistoryBinding;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

/**
 * @Author Leon
 * @Time 2022/07/26
 * @Desc todo 类型待定
 */
public class HistoryAdapter extends BaseViewBindingAdapter<History.Data.Data, ItemHistoryBinding> {
    public HistoryAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemHistoryBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemHistoryBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_history, parent, false));
    }

    @Override
    protected void onBindViewHolder(History.Data.Data data, ItemHistoryBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> {
            switch (data.getHistory().getBusiness()) {
                case "archive":

                    break;
                case "pgc":
                    break;
                case "live":
                    break;
                case "article-list":
                case "article":
                    break;
                default:
                    break;
            }
        });

        binding.title.setText(data.getTitle());

        if ("".equals(data.getAuthorName())) {
            binding.author.setVisibility(View.GONE);
        } else {
            binding.author.setText(data.getAuthorName());
        }

        StringBuilder builder = new StringBuilder();
        if ("article".equals(data.getHistory().getBusiness())) {
            ViewUtils.setImg(context, binding.cover, data.getCovers().get(0));

            binding.progress.setVisibility(View.GONE);
            binding.duration.setVisibility(View.GONE);
        } else {
            // todo 可能出现双行的情况，格式需修改
            ViewUtils.setImg(context, binding.cover, data.getCover());
            if (!"".equals(data.getBadge())) {
                builder.append(data.getBadge()).append("|");
            }

            if (!"".equals(data.getShowTitle())) {
                builder.append("看到").append(data.getShowTitle())
                        .append(data.getProgress() == -1 ? "已看完" : ValueUtils.toMediaDuration(data.getProgress()))
                        .append("|").append(data.getNewDesc());
            } else {
                builder.append(data.getProgress() == -1 ? "已看完" : "看到" + ValueUtils.toMediaDuration(data.getProgress()));
            }

            binding.duration.setText(ValueUtils.toMediaDuration(data.getDuration()));
            binding.progress.setText(builder.toString());
        }
    }
}
