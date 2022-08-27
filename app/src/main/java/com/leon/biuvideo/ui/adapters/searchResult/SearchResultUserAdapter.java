package com.leon.biuvideo.ui.adapters.searchResult;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.home.searchResult.SearchResultUser;
import com.leon.biuvideo.databinding.ItemUserBinding;
import com.leon.biuvideo.ui.activities.publicActivities.UserActivity;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/06
 * @Desc
 */
public class SearchResultUserAdapter extends BaseViewBindingAdapter<SearchResultUser.Data.Result, ItemUserBinding> {
    public SearchResultUserAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemUserBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemUserBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false));
    }

    @Override
    protected void onBindViewHolder(SearchResultUser.Data.Result data, ItemUserBinding binding, int position) {
        binding.container.setOnClickListener(v -> startActivity(UserActivity.class, Map.of(UserActivity.PARAM, String.valueOf(data.getMid()))));

        ViewUtils.setImg(context, binding.face, data.getUpic());
        binding.name.setText(data.getUname());
        binding.sign.setText(data.getUsign());

        int verifyType = data.getOfficialVerify().getType();
        if (verifyType != -1) {
            binding.mark.setVisibility(View.VISIBLE);
            binding.mark.setImageResource(verifyType == 0 ? R.drawable.ic_person_verify : R.drawable.ic_official_verify);
        }
    }
}
