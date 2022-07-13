package com.leon.biuvideo.ui.activities.drawerFunction.partition;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityPartitionBinding;
import com.leon.biuvideo.parser.PartitionParser;
import com.leon.biuvideo.ui.activities.drawerFunction.PopularActivity;

import java.util.Map;

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
        binding.partitionAnime.setOnClickListener(v -> startPage("anime"));
        binding.partitionGuochuang.setOnClickListener(v -> startPage("guochuang"));
        binding.partitionDouga.setOnClickListener(v -> startPage("douga"));
        binding.partitionEnt.setOnClickListener(v -> startPage("ent"));
        binding.partitionArticle.setOnClickListener(v -> startPage("article"));
        binding.partitionMusic.setOnClickListener(v -> startPage("music"));
        binding.partitionDance.setOnClickListener(v -> startPage("dance"));
        binding.partitionDocumentary.setOnClickListener(v -> startPage("documentary"));
        binding.partitionGame.setOnClickListener(v -> startPage("game"));
        binding.partitionTechnology.setOnClickListener(v -> startPage("technology"));
        binding.partitionDigital.setOnClickListener(v -> startPage("digital"));
        binding.partitionLife.setOnClickListener(v -> startPage("life"));
        binding.partitionFood.setOnClickListener(v -> startPage("food"));
        binding.partitionAnimal.setOnClickListener(v -> startPage("animal"));
        binding.partitionKichiku.setOnClickListener(v -> startPage("kichiku"));
        binding.partitionFashion.setOnClickListener(v -> startPage("fashion"));
        binding.partitionCinephile.setOnClickListener(v -> startPage("cinephile"));
        binding.partitionMovie.setOnClickListener(v -> startPage("movie"));
        binding.partitionTeleplay.setOnClickListener(v -> startPage("rank"));
        binding.partitionRank.setOnClickListener(v -> startActivity(PopularActivity.class));
    }

    private void startPage(String partitionName) {
        startActivity(PartitionDetailActivity.class, Map.of(PartitionDetailActivity.PARAM, partitionName));
    }
}