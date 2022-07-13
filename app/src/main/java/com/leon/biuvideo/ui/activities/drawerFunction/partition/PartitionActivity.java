package com.leon.biuvideo.ui.activities.drawerFunction.partition;

import android.widget.Toast;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityPartitionBinding;
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
        binding.partitionAnime.setOnClickListener(v -> startPage("anime", "番剧"));
        binding.partitionGuochuang.setOnClickListener(v -> startPage("guochuang", "国创"));
        binding.partitionDouga.setOnClickListener(v -> startPage("douga", "动画"));
        binding.partitionEnt.setOnClickListener(v -> startPage("ent", "娱乐"));
        binding.partitionArticle.setOnClickListener(v -> Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show());
        binding.partitionMusic.setOnClickListener(v -> startPage("music", "音乐"));
        binding.partitionDance.setOnClickListener(v -> startPage("dance", "舞蹈"));
        binding.partitionDocumentary.setOnClickListener(v -> startPage("documentary", "纪录片"));
        binding.partitionGame.setOnClickListener(v -> startPage("game", "游戏"));
        binding.partitionTechnology.setOnClickListener(v -> startPage("technology", "科技"));
        binding.partitionDigital.setOnClickListener(v -> startPage("digital", "数码"));
        binding.partitionLife.setOnClickListener(v -> startPage("life", "生活"));
        binding.partitionFood.setOnClickListener(v -> startPage("food", "美食"));
        binding.partitionAnimal.setOnClickListener(v -> startPage("animal", "动物圈"));
        binding.partitionKichiku.setOnClickListener(v -> startPage("kichiku", "鬼畜"));
        binding.partitionFashion.setOnClickListener(v -> startPage("fashion", "时尚"));
        binding.partitionCinephile.setOnClickListener(v -> startPage("cinephile", "影视"));
        binding.partitionMovie.setOnClickListener(v -> Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show());
        binding.partitionTeleplay.setOnClickListener(v -> Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show());

        // todo(由于格式问题，后期更新加入)
//        binding.partitionArticle.setOnClickListener(v -> startPage("article", "专栏"));
//        binding.partitionMovie.setOnClickListener(v -> startPage("movie", "电影"));
//        binding.partitionTeleplay.setOnClickListener(v -> startPage("tv", "电视剧"));
        binding.partitionRank.setOnClickListener(v -> startActivity(PopularActivity.class));
    }

    private void startPage(String partitionName, String partitionTitle) {
        startActivity(PartitionDetailActivity.class, Map.of(PartitionDetailActivity.PARAM_NAME, partitionName,
                PartitionDetailActivity.PARAM_TITLE, partitionTitle));
    }
}