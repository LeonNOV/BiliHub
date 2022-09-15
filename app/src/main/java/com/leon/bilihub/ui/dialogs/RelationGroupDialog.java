package com.leon.bilihub.ui.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.leon.bilihub.beans.account.RelationTags;
import com.leon.bilihub.databinding.DialogRelationGroupBinding;
import com.leon.bilihub.http.ApiHelper;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.drawer.relation.RelationGroupAdapter;
import com.leon.bilihub.ui.widget.loader.RecyclerViewLoader;
import com.leon.bilihub.utils.PreferenceUtils;

import java.util.ArrayList;

/**
 * @Author Leon
 * @Time 2022/09/15
 * @Desc
 */
public class RelationGroupDialog extends BottomSheetDialog {
    private DialogRelationGroupBinding binding;
    private RelationGroupAdapter adapter;
    private RetrofitClient client;

    public RelationGroupDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DialogRelationGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(attributes);

        initView();
    }

    private void initView() {
        client = new RetrofitClient(BaseUrl.API, getContext());
        adapter = new RelationGroupAdapter(getContext(), false);
        RecyclerViewLoader<RelationTags, RelationTags.Data> loader = new RecyclerViewLoader<>(binding.content, adapter);
        loader.setGuide(RelationTags::getData)
                .setObservable(client.getHttpApi().getUserRelationGroups())
                .obtain(false);

        binding.confirm.setOnClickListener(v -> {
            ArrayList<String> selectedGroup = new ArrayList<>();
            for (RelationTags.Data data : adapter.getDataSource()) {
                if (data.isSelected()) {
                    selectedGroup.add(data.getTagid());
                }
            }

            Toast.makeText(getContext(), selectedGroup.toString(), Toast.LENGTH_SHORT).show();
        });
        binding.add.setOnClickListener(v -> new EditorDialog(getContext(), "分组名称", this::createGroup).show());
    }

    private void createGroup(String groupName) {
        new ApiHelper<>(client.getPostApi().createRelationGroup(groupName, PreferenceUtils.getCsrf(getContext()))).setOnResult(createGroup -> {
            if (createGroup.getCode() != 0 || createGroup.getData() == null) {
                Toast.makeText(getContext(), createGroup.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                adapter.appendTail(new RelationTags.Data(0, groupName, createGroup.getData().getTagid(), "", false));
            }
        }).doIt();
    }
}
