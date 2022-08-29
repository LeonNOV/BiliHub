package com.leon.bilihub.ui.activities.drawerFunction.partition;

import android.os.Bundle;

import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.databinding.ActivityPartitionBinding;

/**
 * @Author Leon
 * @Time 2022/6/21
 * @Desc
 */
public class PartitionActivity extends BaseActivity<ActivityPartitionBinding> {

    @Override
    public ActivityPartitionBinding getViewBinding() {
        return ActivityPartitionBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        binding.douga.setOnClickListener(v -> startPage(1, "动画"));
        binding.game.setOnClickListener(v -> startPage(4, "游戏"));
        binding.kichiku.setOnClickListener(v -> startPage(119, "鬼畜"));
        binding.music.setOnClickListener(v -> startPage(3, "音乐"));
        binding.dance.setOnClickListener(v -> startPage(129, "舞蹈"));
        binding.cinephile.setOnClickListener(v -> startPage(181, "影视"));
        binding.ent.setOnClickListener(v -> startPage(5, "娱乐"));
        binding.knowledge.setOnClickListener(v -> startPage(36, "知识"));
        binding.tech.setOnClickListener(v -> startPage(188, "科技"));
        binding.information.setOnClickListener(v -> startPage(202, "资讯"));
        binding.food.setOnClickListener(v -> startPage(211, "美食"));
        binding.life.setOnClickListener(v -> startPage(160, "生活"));
        binding.car.setOnClickListener(v -> startPage(223, "汽车"));
        binding.fashion.setOnClickListener(v -> startPage(155, "时尚"));
        binding.sports.setOnClickListener(v -> startPage(234, "运动"));
        binding.animal.setOnClickListener(v -> startPage(217, "动物圈"));
    }

    private void startPage(int tid, String partitionTitle) {
        Bundle bundle = new Bundle();
        bundle.putInt(PartitionDetailActivity.PARAM_A, tid);
        bundle.putString(PartitionDetailActivity.PARAM_B, partitionTitle);

        startActivity(PartitionDetailActivity.class, bundle);
    }
}