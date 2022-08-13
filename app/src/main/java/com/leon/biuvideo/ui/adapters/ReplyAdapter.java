package com.leon.biuvideo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.resources.Reply;
import com.leon.biuvideo.databinding.ItemReplyBinding;
import com.leon.biuvideo.ui.activities.publicActivities.UserActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Locale;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/13
 * @Desc
 */
public class ReplyAdapter extends BaseViewBindingAdapter<Reply.Data.Reply, ItemReplyBinding> {
    public ReplyAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemReplyBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemReplyBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_reply, parent, false));
    }

    @Override
    protected void onBindViewHolder(Reply.Data.Reply data, ItemReplyBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> Toast.makeText(context, "oid: " + data.getOid(), Toast.LENGTH_SHORT).show());

        ViewUtils.setImg(context, binding.face, data.getMember().getAvatar());
        binding.face.setOnClickListener(v -> startActivity(UserActivity.class, Map.of(UserActivity.PARAM, data.getMid())));
        binding.name.setText(data.getMember().getUname());
        if (data.getMember().getVip().getVipStatus() == 1) {
            binding.name.setTextColor(context.getColor(R.color.BiliBili_pink));
        }

        int roleVerify = data.getMember().getOfficialVerify().getType();
        if (roleVerify != -1) {
            binding.verify.setVisibility(View.VISIBLE);
            binding.verify.setImageResource(roleVerify == 1 ? R.drawable.ic_person_verify : R.drawable.ic_official_verify);
        }

        binding.like.setOnClickListener(v -> Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show());
        binding.likeStr.setText(ValueUtils.generateCN(data.getLike()));

        binding.content.setText(data.getContent().getMessage());
        binding.pubTime.setText(ValueUtils.generateTime(data.getCtime(), "yyyy-MM-dd HH:mm", true));
        binding.location.setText(data.getReplyControl().getLocation());
        if (data.getUpAction().getLike()) {
            binding.upAction.setVisibility(View.VISIBLE);
        }

        binding.count.setOnClickListener(v -> Toast.makeText(context, "reply detail", Toast.LENGTH_SHORT).show());
        if (data.getRcount() > 0) {
            binding.count.setVisibility(View.VISIBLE);
            if (data.getUpAction().getReply()) {
                binding.countStr.setText(String.format(Locale.CHINESE, "UP主等人共%d条回复", data.getRcount()));
            } else {
                binding.countStr.setText(String.format(Locale.CHINESE, "共%d条回复", data.getRcount()));
            }
        }
    }
}
