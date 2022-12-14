package com.leon.bilihub.ui.fragments.videoFragments;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseFragment.BaseFragment;
import com.leon.bilihub.beans.publicBeans.resources.video.PgcDetail;
import com.leon.bilihub.beans.publicBeans.resources.video.PgcRelation;
import com.leon.bilihub.databinding.FragmentMediaPgcInfoBinding;
import com.leon.bilihub.http.ApiHelper;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.Quality;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.model.VideoPlayerModel;
import com.leon.bilihub.ui.adapters.video.pgc.PgcEpisodeAdapter;
import com.leon.bilihub.ui.adapters.video.pgc.PgcRecommendAdapter;
import com.leon.bilihub.ui.adapters.video.pgc.PgcSeasonAdapter;
import com.leon.bilihub.ui.adapters.video.pgc.PgcSectionAdapter;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;
import com.leon.bilihub.wraps.VideoResourceWrap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/21
 * @Desc 1：番剧<br/>2：电影<br/>3：纪录片<br/>4：国创<br/>5：电视剧<br/>7：综艺
 */
public class MediaPgcInfoFragment extends BaseFragment<FragmentMediaPgcInfoBinding> {
    private final PgcDetail.Result data;

    private final Map<Integer, List<PgcDetail.Result.Section>> seasonSectionMap = new HashMap<>();
    private final List<PgcDetail.Result.Section> publicSectionList = new ArrayList<>();
    private HttpApi httpApi;

    private VideoPlayerModel videoPlayerModel;
    private Observer<String> videoPgcSeasonObserver;
    private Observer<Integer> videoPgcEpisodeObserver;

    private PgcSeasonAdapter pgcSeasonAdapter;
    private PgcEpisodeAdapter episodeAdapter;
    private PgcSectionAdapter pgcSectionAdapter;
    private PgcRecommendAdapter pgcRecommendAdapter;

    public MediaPgcInfoFragment(PgcDetail.Result data) {
        this.data = data;
    }

    @Override
    public FragmentMediaPgcInfoBinding getViewBinding() {
        return FragmentMediaPgcInfoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        videoPlayerModel = new ViewModelProvider(ViewUtils.scanForActivity(context)).get(VideoPlayerModel.class);

        videoPgcSeasonObserver = this::updateInfoContent;
        videoPlayerModel.getVideoPgcSeason().observeForever(videoPgcSeasonObserver);

        videoPgcEpisodeObserver = episodeId -> {
            updateRelation(episodeId);
            setSection(episodeId);
        };
        videoPlayerModel.getVideoPgcEpisode().observeForever(videoPgcEpisodeObserver);

        httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();

        setSeason(data.getSeasons(), data.getSeasonId());
        setInfoContent(data);
    }

    /**
     * 根据seasonId 获取 简介界面数据
     *
     * @param seasonId seasonId
     */
    private void updateInfoContent(String seasonId) {
        new ApiHelper<>(httpApi.getPgcDetail(seasonId)).setOnResult(pgcDetail -> setInfoContent(pgcDetail.getResult())).doIt();
    }

    /**
     * 设置简介界面数据
     *
     * @param data {@link PgcDetail.Result}
     */
    public void setInfoContent(PgcDetail.Result data) {
        binding.title.setText(data.getTitle());
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
        binding.shareStr.setText(ValueUtils.generateCN(data.getStat().getShare()));
        binding.favoriteStr.setText(ValueUtils.generateCN(data.getStat().getFavorite()));

        // empty只出现在片源为预告的情况下
        processSection(data.getSection());
        if (!data.getEpisodes().isEmpty()) {
            PgcDetail.Result.Episode episode = data.getEpisodes().get(0);
            videoPlayerModel.getVideoResource().setValue(new VideoResourceWrap(episode.getBvid(), episode.getCid(), Quality.Q80));
            updateRelation(data.getEpisodes().get(0).getId());
            setEpisode(data.getEpisodes(), data.getType());
            setSection(data.getEpisodes().get(0).getId());
        } else {
            PgcDetail.Result.Stat stat = data.getStat();
            binding.likeStr.setText(ValueUtils.generateCN(stat.getLikes()));
            binding.coinStr.setText(ValueUtils.generateCN(stat.getCoins()));
            binding.favoriteStr.setText(ValueUtils.generateCN(stat.getFavorite()));
            binding.shareStr.setText(ValueUtils.generateCN(stat.getShare()));

            if (episodeAdapter != null) {
                episodeAdapter.removeAll();
            }
            binding.episodeContainer.setVisibility(View.GONE);

            if (pgcSectionAdapter != null) {
                pgcSectionAdapter.removeAll();
            }
            binding.section.setVisibility(View.GONE);

            videoPlayerModel.getVideoResource().setValue(null);
            videoPlayerModel.getVideoRecommend().setValue(null);
        }

        setRecommend(data.getSeasonId());
    }

    /**
     * 更新选集与当前用户的关系
     *
     * @param epId 选集ID
     */
    private void updateRelation(int epId) {
        new ApiHelper<>(httpApi.getPgcRelation(epId)).setOnResult(pgcRelation -> {
            PgcRelation.Data.UserCommunity community = pgcRelation.getData().getUserCommunity();
            PgcRelation.Data.Stat stat = pgcRelation.getData().getStat();

            binding.likeImg.setSelected(community.getLike() == 1);
            binding.likeStr.setText(ValueUtils.generateCN(stat.getLike()));

            binding.coinImg.setSelected(community.getCoinNumber() > 0);
            binding.coinStr.setText(ValueUtils.generateCN(stat.getCoin()));

            binding.favoriteImg.setSelected(community.getFavorite() == 1);
        }).doIt();
    }

    /**
     * 处理section
     */
    private void processSection(List<PgcDetail.Result.Section> sectionList) {
        if (sectionList != null) {
            if (!seasonSectionMap.isEmpty()) {
                seasonSectionMap.clear();
            }

            if (!publicSectionList.isEmpty()) {
                publicSectionList.clear();
            }

            for (PgcDetail.Result.Section section : sectionList) {
                if (section.getEpisodeIds() != null && !section.getEpisodeIds().isEmpty()) {
                    for (Integer episodeId : section.getEpisodeIds()) {
                        if (!seasonSectionMap.containsKey(episodeId)) {
                            seasonSectionMap.put(episodeId, new ArrayList<>());
                        }
                        seasonSectionMap.get(episodeId).add(section);
                    }
                } else {
                    publicSectionList.add(section);
                }
            }
        }
    }

    private void setSeason(List<PgcDetail.Result.Season> seasonList, String seasonId) {
        if (seasonList != null && seasonList.size() > 1) {
            binding.seasons.setVisibility(View.VISIBLE);

            int selectedColor = context.getColor(R.color.blue);
            int unselectedColor = context.getColor(R.color.infoColor);

            int selectedPosition = 0;
            for (int i = 0; i < seasonList.size(); i++) {
                PgcDetail.Result.Season season = seasonList.get(i);
                PgcDetail.Result.Season.ItemState itemState;
                if (seasonId.equals(season.getSeasonId())) {
                    itemState = new PgcDetail.Result.Season.ItemState(selectedColor, true);
                    selectedPosition = i;
                } else {
                    itemState = new PgcDetail.Result.Season.ItemState(unselectedColor, false);
                }
                season.setItemState(itemState);
            }

            if (pgcSeasonAdapter == null) {
                pgcSeasonAdapter = new PgcSeasonAdapter(context, selectedPosition);
                ViewUtils.listInitializer(binding.seasons, pgcSeasonAdapter);
            } else {
                pgcSeasonAdapter.removeAll();
            }

            pgcSeasonAdapter.setOnSelectedListener(integer -> {
                PgcDetail.Result.Season.ItemState itemState = seasonList.get(integer).getItemState();
                itemState.setTitleColor(unselectedColor);
                itemState.setSelected(false);

                pgcSeasonAdapter.notifyItemChanged(integer);
            });
            pgcSeasonAdapter.appendHead(seasonList);
        }
    }

    private void setEpisode(List<PgcDetail.Result.Episode> episodeList, int type) {
        if (episodeList != null && episodeList.size() > 1) {
            binding.episodeContainer.setVisibility(View.VISIBLE);

            int selectedColor = context.getColor(R.color.blue);
            int unselectedColor = context.getColor(R.color.infoColor);

            episodeList.forEach(episode -> episode.setItemState(new PgcDetail.Result.Episode.ItemState(unselectedColor, false)));
            PgcDetail.Result.Episode.ItemState state = episodeList.get(0).getItemState();
            state.setEpColor(selectedColor);
            state.setEpSelected(true);

            if (episodeAdapter == null) {
                episodeAdapter = new PgcEpisodeAdapter(context, type, 0);
                ViewUtils.listInitializer(binding.episode, episodeAdapter);
            } else {
                episodeAdapter.removeAll();
            }

            episodeAdapter.setOnSelectedListener(integer -> {
                PgcDetail.Result.Episode.ItemState itemState = episodeList.get(integer).getItemState();
                itemState.setEpColor(unselectedColor);
                itemState.setEpSelected(false);

                episodeAdapter.notifyItemChanged(integer);
            });
            episodeAdapter.appendHead(episodeList);
        }
    }

    private void setSection(int episodeId) {
        if (!seasonSectionMap.isEmpty() || !publicSectionList.isEmpty()) {
            binding.section.setVisibility(View.VISIBLE);

            List<PgcDetail.Result.Section> resultSectionList = new ArrayList<>();
            List<PgcDetail.Result.Section> epSectionList = seasonSectionMap.get(episodeId);

            if (epSectionList != null) {
                resultSectionList.addAll(epSectionList);
            }
            resultSectionList.addAll(publicSectionList);

            if (pgcSectionAdapter == null) {
                pgcSectionAdapter = new PgcSectionAdapter(context);
                ViewUtils.linkAdapter(binding.section, pgcSectionAdapter);
            } else {
                pgcSectionAdapter.removeAll();
            }

            pgcSectionAdapter.appendHead(resultSectionList);
        }
    }

    private void setRecommend(String seasonId) {
        new ApiHelper<>(httpApi.getPgcRecommend(seasonId)).setOnResult(pgcRecommend -> {
            if (pgcRecommendAdapter == null) {
                pgcRecommendAdapter = new PgcRecommendAdapter(context);
                ViewUtils.listInitializer(binding.recommend, pgcRecommendAdapter);
            } else {
                pgcRecommendAdapter.removeAll();
            }
            pgcRecommendAdapter.appendHead(pgcRecommend.getData().getSeason());
        }).doIt();
    }

    @Override
    public void onDestroy() {
        videoPlayerModel.getVideoPgcSeason().removeObserver(videoPgcSeasonObserver);
        videoPlayerModel.getVideoPgcEpisode().removeObserver(videoPgcEpisodeObserver);

        super.onDestroy();
    }
}
