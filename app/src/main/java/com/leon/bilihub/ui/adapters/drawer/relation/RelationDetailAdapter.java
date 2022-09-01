package com.leon.bilihub.ui.adapters.drawer.relation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.account.RelationDetail;
import com.leon.bilihub.databinding.ItemUserBinding;
import com.leon.bilihub.ui.activities.publicActivities.UserActivity;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/13
 * @Desc
 */
public class RelationDetailAdapter extends ViewBindingAdapter<RelationDetail.Data, ItemUserBinding> {
    public RelationDetailAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemUserBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemUserBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false));
    }

    @Override
    protected void onBindViewHolder(RelationDetail.Data data, ItemUserBinding binding, int position) {
        binding.container.setOnClickListener(v -> startActivity(UserActivity.class, Map.of(UserActivity.PARAM, String.valueOf(data.getMid()))));

        ViewUtils.setImg(context, binding.face, data.getFace());
        binding.name.setText(data.getUname());
        binding.sign.setText(data.getSign());

        int verifyType = data.getOfficialVerify().getType();
        if (verifyType != -1) {
            binding.mark.setVisibility(View.VISIBLE);
            binding.mark.setImageResource(verifyType == 0 ? R.drawable.ic_person_verify : R.drawable.ic_official_verify);
        }
    }
}
