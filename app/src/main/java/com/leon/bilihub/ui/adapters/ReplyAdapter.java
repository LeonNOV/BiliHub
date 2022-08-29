package com.leon.bilihub.ui.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.resources.Reply;
import com.leon.bilihub.beans.publicBeans.resources.SubReply;
import com.leon.bilihub.databinding.ItemReplyBinding;
import com.leon.bilihub.ui.activities.publicActivities.UserActivity;
import com.leon.bilihub.ui.dialogs.ReplyDetailDialog;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Locale;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/13
 * @Desc
 */
public class ReplyAdapter<T extends Parcelable> extends BaseViewBindingAdapter<T, ItemReplyBinding> {
    private final boolean isSubReply;

    public ReplyAdapter(Context context, boolean isSubReply) {
        super(context);
        this.isSubReply = isSubReply;
    }

    @Override
    protected ItemReplyBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemReplyBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_reply, parent, false));
    }

    @Override
    protected void onBindViewHolder(T data, ItemReplyBinding binding, int position) {
        if (isSubReply) {
            setSubReply(binding, (SubReply.Data.Reply) data);
        } else {
            setReply(binding, (Reply.Data.Reply) data);
        }
    }

    private void setReply(ItemReplyBinding binding, Reply.Data.Reply data) {
        binding.getRoot().setOnClickListener(v -> Toast.makeText(context, "oid: " + data.getOid(), Toast.LENGTH_SHORT).show());

        ViewUtils.setImg(context, binding.face, data.getMember().getAvatar());
        binding.face.setOnClickListener(v -> startActivity(UserActivity.class, Map.of(UserActivity.PARAM, data.getMid())));
        binding.name.setText(data.getMember().getUname());
        if (data.getMember().getVip().getVipStatus() == 1) {
            binding.name.setTextColor(context.getColor(R.color.pink));
        }

        int roleVerify = data.getMember().getOfficialVerify().getType();
        if (roleVerify != -1) {
            binding.verify.setVisibility(View.VISIBLE);
            binding.verify.setImageResource(roleVerify == 1 ? R.drawable.ic_person_verify : R.drawable.ic_official_verify);
        }

        binding.like.setOnClickListener(v -> Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show());
        binding.likeStr.setText(ValueUtils.generateCN(data.getLike()));
        binding.content.setContent(data.getContent().getMessage());
        binding.pubTime.setText(ValueUtils.generateTime(data.getCtime(), "yyyy-MM-dd HH:mm", true));
        binding.location.setText(data.getReplyControl().getLocation());
        if (data.getUpAction().getLike()) {
            binding.upAction.setVisibility(View.VISIBLE);
        }

        binding.count.setOnClickListener(v -> new ReplyDetailDialog(context, String.valueOf(data.getOid()), data.getRpidStr()).show());

        if (data.getRcount() > 0) {
            binding.count.setVisibility(View.VISIBLE);
            if (data.getUpAction().getReply()) {
                binding.countStr.setText(String.format(Locale.CHINESE, "UP主等人共%d条回复", data.getRcount()));
            } else {
                binding.countStr.setText(String.format(Locale.CHINESE, "共%d条回复", data.getRcount()));
            }

        }
    }

    private void setSubReply(ItemReplyBinding binding, SubReply.Data.Reply data) {
        binding.getRoot().setOnClickListener(v -> Toast.makeText(context, "oid: " + data.getOid(), Toast.LENGTH_SHORT).show());

        ViewUtils.setImg(context, binding.face, data.getMember().getAvatar());
        binding.face.setOnClickListener(v -> startActivity(UserActivity.class, Map.of(UserActivity.PARAM, data.getMid())));
        binding.name.setText(data.getMember().getUname());
        if (data.getMember().getVip().getVipStatus() == 1) {
            binding.name.setTextColor(context.getColor(R.color.pink));
        }

        int roleVerify = data.getMember().getOfficialVerify().getType();
        if (roleVerify != -1) {
            binding.verify.setVisibility(View.VISIBLE);
            binding.verify.setImageResource(roleVerify == 1 ? R.drawable.ic_person_verify : R.drawable.ic_official_verify);
        }

        binding.like.setOnClickListener(v -> Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show());
        binding.likeStr.setText(ValueUtils.generateCN(data.getLike()));
        binding.content.setContent(data.getContent().getMessage());
        binding.pubTime.setText(ValueUtils.generateTime(data.getCtime(), "yyyy-MM-dd HH:mm", true));
        binding.location.setText(data.getReplyControl().getLocation());
        if (data.getUpAction().getLike()) {
            binding.upAction.setVisibility(View.VISIBLE);
        }
    }
}
