package com.leon.bilihub.ui.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.leon.bilihub.R;
import com.leon.bilihub.base.baseActivity.ActivityManager;
import com.leon.bilihub.beans.publicBeans.resources.SubReply;
import com.leon.bilihub.databinding.DialogReplyDetailBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.activities.publicActivities.UserActivity;
import com.leon.bilihub.ui.adapters.video.ReplyAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/20
 * @Desc
 */
public class ReplyDetailDialog extends BottomSheetDialog {
    private final String oid;
    private final String rootId;
    private int pageNum = 0;

    private DialogReplyDetailBinding binding;

    public ReplyDetailDialog(@NonNull Context context, String oid, String rootId) {
        super(context, R.style.BottomSheetDialogBg);
        this.oid = oid;
        this.rootId = rootId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DialogReplyDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.back.setOnClickListener(v -> dismiss());

        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(attributes);

        HttpApi httpApi = new RetrofitClient(BaseUrl.API, getContext()).getHttpApi();
        PaginationLoader<SubReply, SubReply.Data.Reply> loader = new PaginationLoader<>(binding.subReply, new ReplyAdapter<>(getContext(), true));
        loader.setGuide(subReply -> {
            if (pageNum == 1) {
                initMain(subReply.getData().getRoot());
            }

            return subReply.getData().getReplies();
        });
        loader.setUpdateInterface(loadType -> httpApi.getSubReply(oid, rootId, ++pageNum)).firstObtain();
    }

    private void initMain(SubReply.Data.Root root) {
        SubReply.Data.Root.Member member = root.getMember();
        ViewUtils.setImg(getContext(), binding.face, member.getAvatar());
        binding.face.setOnClickListener(v -> ActivityManager.startActivity(getContext(), UserActivity.class, Map.of(UserActivity.PARAM, member.getMid())));
        binding.name.setText(member.getUname());
        if (member.getVip().getVipStatus() == 1) {
            binding.name.setTextColor(getContext().getColor(R.color.pink));
        }

        int roleVerify = member.getOfficialVerify().getType();
        if (roleVerify != -1) {
            binding.verify.setVisibility(View.VISIBLE);
            binding.verify.setImageResource(roleVerify == 1 ? R.drawable.ic_person_verify : R.drawable.ic_official_verify);
        }

        binding.like.setOnClickListener(v -> Toast.makeText(getContext(), "开发中…", Toast.LENGTH_SHORT).show());
        binding.likeStr.setText(ValueUtils.generateCN(root.getLike()));
        binding.content.setContent(root.getContent().getMessage());
        binding.pubTime.setText(ValueUtils.generateTime(root.getCtime(), "yyyy-MM-dd HH:mm", true));
        binding.location.setText(root.getReplyControl().getLocation());
        if (root.getUpAction().getLike()) {
            binding.upAction.setVisibility(View.VISIBLE);
        }
    }
}
