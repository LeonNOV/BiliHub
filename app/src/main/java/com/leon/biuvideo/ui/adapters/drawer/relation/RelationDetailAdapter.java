package com.leon.biuvideo.ui.adapters.drawer.relation;

import android.content.Context;
import android.widget.Toast;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.account.RelationDetail;
import com.leon.biuvideo.databinding.ItemUserBinding;
import com.leon.biuvideo.ui.activities.publicActivities.UserActivity;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/13
 * @Desc
 */
public class RelationDetailAdapter extends BaseViewBindingAdapter<RelationDetail.Data, ItemUserBinding> {
    public RelationDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayout(int viewType) {
        return R.layout.item_user;
    }

    @Override
    protected ItemUserBinding getItemViewBinding() {
        return ItemUserBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(RelationDetail.Data data, ItemUserBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> ActivityManager.startActivity(context, UserActivity.class, Map.of(UserActivity.PARAM, String.valueOf(data.getMid()))));

        ViewUtils.setImg(context, binding.face, data.getFace());
        binding.name.setText(data.getUname());
        binding.sign.setText(data.getSign());
        binding.action.setOnClickListener(v -> {
            Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show();
        });
    }
}
