package com.leon.biuvideo.ui.fragments.videoFragments;

import android.util.ArraySet;
import android.view.View;
import android.widget.Toast;

import com.leon.biuvideo.base.baseFragment.BaseFragment;
import com.leon.biuvideo.beans.publicBeans.resources.video.PgcDetail;
import com.leon.biuvideo.beans.publicBeans.resources.video.PgcRelation;
import com.leon.biuvideo.databinding.FragmentMediaPgcInfoBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.http.TestValue;
import com.leon.biuvideo.ui.adapters.video.pgc.PgcEpisodeAdapter;
import com.leon.biuvideo.ui.adapters.video.pgc.PgcSeasonAdapter;
import com.leon.biuvideo.ui.adapters.video.pgc.PgcSectionAdapter;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @Author Leon
 * @Time 2022/08/21
 * @Desc
 */
public class MediaPgcInfoFragment extends BaseFragment<FragmentMediaPgcInfoBinding> {
    private final PgcDetail.Result data;

    private int episodeId;
    private Map<Integer, List<Integer>> sectionMap;
    private Set<Integer> publicSectionList;

    public MediaPgcInfoFragment(PgcDetail.Result data) {
        this.data = data;
    }

    @Override
    public FragmentMediaPgcInfoBinding getViewBinding() {
        return FragmentMediaPgcInfoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        binding.title.setText(data.getTitle());
        data.getRights();

        // 1：番剧
        // 2：电影
        // 3：纪录片
        // 4：国创
        // 5：电视剧
        // 7：综艺
        binding.newEp.setText(data.getNewEp().getDesc());
        binding.play.setText(String.format(Locale.CHINESE, "%s播放", ValueUtils.generateCN(data.getStat().getViews())));
        binding.following.setText(String.format(Locale.CHINESE, "%s追剧", ValueUtils.generateCN(data.getStat().getFavorites())));

        if (data.getRating() == null) {
            binding.scoreContainer.setVisibility(View.GONE);
            binding.noScore.setVisibility(View.VISIBLE);
        } else {
            binding.score.setText(String.valueOf(data.getRating().getScore()));
        }

        binding.detail.setOnClickListener(v -> Toast.makeText(context, "detail", Toast.LENGTH_SHORT).show());

        binding.likeStr.setText(ValueUtils.generateCN(data.getStat().getLikes()));
        binding.coinStr.setText(ValueUtils.generateCN(data.getStat().getCoins()));
        binding.favoriteStr.setText(ValueUtils.generateCN(data.getStat().getFavorite()));
        binding.shareStr.setText(ValueUtils.generateCN(data.getStat().getShare()));

        processSection();

        setSeason(data.getSeasons());
        setEpisode(data.getEpisodes());
        setSection(data.getSection());
    }

    /**
     * 更新选集与当前用户的关系
     *
     * @param epId  选集ID
     */
    private void updateRelation(String epId) {
        new ApiHelper<>(new RetrofitClient(BaseUrl.API, Map.of(HttpApi.COOKIE, TestValue.TEST_COOKIE)).getHttpApi().getPgcRelation(epId)).setOnResult(pgcRelation -> {
            PgcRelation.Data.UserCommunity community = pgcRelation.getData().getUserCommunity();
            binding.shareImg.setSelected(community.getLike() == 1);
            binding.coinImg.setSelected(community.getCoinNumber() >= 1);
            binding.favoriteImg.setSelected(community.getFavorite() == 1);
        }).doIt();
    }

    private void setSeason(List<PgcDetail.Result.Season> seasonList) {
        if (seasonList.size() > 1) {
            binding.seasons.setVisibility(View.VISIBLE);

            PgcSeasonAdapter adapter = new PgcSeasonAdapter(context);
            adapter.appendHead(seasonList);

            ViewUtils.listInitializer(binding.seasons, adapter);
        }
    }

    private void setEpisode(List<PgcDetail.Result.Episode> episodeList) {
        if (episodeList.size() > 1) {
            binding.episodeContainer.setVisibility(View.VISIBLE);

            PgcEpisodeAdapter adapter = new PgcEpisodeAdapter(context, data.getType());
            adapter.appendHead(episodeList);

            ViewUtils.listInitializer(binding.episode, adapter);
        }
    }

    private void setSection(List<PgcDetail.Result.Section> sectionList) {
        if (!sectionList.isEmpty()) {
            binding.section.setVisibility(View.VISIBLE);
            List<PgcDetail.Result.Section> resultSection = new ArrayList<>();

            List<Integer> list = sectionMap.get(episodeId);
            if (list != null) {
                list.forEach(id -> resultSection.add(sectionList.get(id)));
            }
            publicSectionList.forEach(id -> resultSection.add(sectionList.get(id)));

            PgcSectionAdapter adapter = new PgcSectionAdapter(context);
            adapter.appendHead(resultSection);

            ViewUtils.listInitializer(binding.section, adapter);
        }
    }

    /**
     * 处理section
     */
    private void processSection() {
        sectionMap = new HashMap<>();
        publicSectionList = new ArraySet<>();

        for (int i = 0; i < data.getSection().size(); i++) {
            PgcDetail.Result.Section section = data.getSection().get(i);
            if (section.getEpisodeIds() != null && !section.getEpisodeIds().isEmpty()) {
                for (Integer id : section.getEpisodeIds()) {
                    if (!sectionMap.containsKey(id)) {
                        sectionMap.put(id, new ArrayList<>());
                    }

                    sectionMap.get(id).add(i);
                }
            } else {
                publicSectionList.add(i);
            }
        }
    }
}
