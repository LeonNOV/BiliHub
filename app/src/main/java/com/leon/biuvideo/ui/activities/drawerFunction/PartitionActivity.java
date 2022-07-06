package com.leon.biuvideo.ui.activities.drawerFunction;

import android.os.Bundle;

import com.leon.biuvideo.actions.partitionActions.AnimalAction;
import com.leon.biuvideo.actions.partitionActions.AnimeAction;
import com.leon.biuvideo.actions.partitionActions.RankAction;
import com.leon.biuvideo.base.baseAction.BaseAction;
import com.leon.biuvideo.actions.partitionActions.ArticleAction;
import com.leon.biuvideo.actions.partitionActions.CinephileAction;
import com.leon.biuvideo.actions.partitionActions.DanceAction;
import com.leon.biuvideo.actions.partitionActions.DigitalAction;
import com.leon.biuvideo.actions.partitionActions.DocumentaryAction;
import com.leon.biuvideo.actions.partitionActions.DougaAction;
import com.leon.biuvideo.actions.partitionActions.EntAction;
import com.leon.biuvideo.actions.partitionActions.FashionAction;
import com.leon.biuvideo.actions.partitionActions.FoodAction;
import com.leon.biuvideo.actions.partitionActions.GameAction;
import com.leon.biuvideo.actions.partitionActions.GuochuangAction;
import com.leon.biuvideo.actions.partitionActions.KichikuAction;
import com.leon.biuvideo.actions.partitionActions.LifeAction;
import com.leon.biuvideo.actions.partitionActions.MovieAction;
import com.leon.biuvideo.actions.partitionActions.MusicAction;
import com.leon.biuvideo.actions.partitionActions.TechnologyAction;
import com.leon.biuvideo.actions.partitionActions.TeleplayAction;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityPartitionBinding;
import com.leon.biuvideo.ui.activities.actionActivities.DataListActivity;
import com.leon.biuvideo.ui.activities.actionActivities.PartitionDataListActivity;

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
        binding.partitionAnime.setOnClickListener(v -> functionGo(AnimeAction.class));
        binding.partitionGuochuang.setOnClickListener(v -> functionGo(GuochuangAction.class));
        binding.partitionDouga.setOnClickListener(v -> functionGo(DougaAction.class));
        binding.partitionEnt.setOnClickListener(v -> functionGo(EntAction.class));
        binding.partitionArticle.setOnClickListener(v -> functionGo(ArticleAction.class));
        binding.partitionMusic.setOnClickListener(v -> functionGo(MusicAction.class));
        binding.partitionDance.setOnClickListener(v -> functionGo(DanceAction.class));
        binding.partitionDocumentary.setOnClickListener(v -> functionGo(DocumentaryAction.class));
        binding.partitionGame.setOnClickListener(v -> functionGo(GameAction.class));
        binding.partitionTechnology.setOnClickListener(v -> functionGo(TechnologyAction.class));
        binding.partitionDigital.setOnClickListener(v -> functionGo(DigitalAction.class));
        binding.partitionLife.setOnClickListener(v -> functionGo(LifeAction.class));
        binding.partitionFood.setOnClickListener(v -> functionGo(FoodAction.class));
        binding.partitionAnimal.setOnClickListener(v -> functionGo(AnimalAction.class));
        binding.partitionKichiku.setOnClickListener(v -> functionGo(KichikuAction.class));
        binding.partitionFashion.setOnClickListener(v -> functionGo(FashionAction.class));
        binding.partitionCinephile.setOnClickListener(v -> functionGo(CinephileAction.class));
        binding.partitionMovie.setOnClickListener(v -> functionGo(MovieAction.class));
        binding.partitionTeleplay.setOnClickListener(v -> functionGo(TeleplayAction.class));
        binding.partitionRank.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(BaseAction.ACTION, RankAction.class.getName());
            ActivityManager.startActivity(context, DataListActivity.class, bundle);
        });
    }

    /**
     * close the drawer
     */
    private void functionGo(Class<? extends BaseAction> targetClass) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseAction.ACTION, targetClass.getName());
        ActivityManager.startActivity(context, PartitionDataListActivity.class, bundle);
    }
}