package com.leon.biuvideo.ui.activities.drawerFunction.partition;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.Partition;
import com.leon.biuvideo.databinding.ActivityPartitionDetailBinding;
import com.leon.biuvideo.parser.PartitionParser;
import com.leon.biuvideo.ui.fragments.PartitionDataListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2022/7/13
 * @Desc
 */
public class PartitionDetailActivity extends BaseActivity<ActivityPartitionDetailBinding> {
    public static final String PARAM = "name";

    @Override
    public ActivityPartitionDetailBinding getViewBinding() {
        return ActivityPartitionDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        String partitionName = params.getString(PARAM);

        ArrayList<Partition> partitionData = PartitionParser.Companion.getPartitionData(partitionName);
        if (partitionData != null) {
            List<Fragment> fragments = new ArrayList<>(partitionData.size());
            for (Partition partition : partitionData) {
                fragments.add(new PartitionDataListFragment(partition));
            }


        } else {
            backPressed();
            Toast.makeText(context, "无子页面", Toast.LENGTH_SHORT).show();
        }
    }
}