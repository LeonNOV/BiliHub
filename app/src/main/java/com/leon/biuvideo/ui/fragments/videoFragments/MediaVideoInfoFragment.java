package com.leon.biuvideo.ui.fragments.videoFragments;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseFragment.BaseFragment;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoDetail;
import com.leon.biuvideo.databinding.FragmentMediaVideoInfoBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.model.VideoPlayerModel;
import com.leon.biuvideo.ui.activities.publicActivities.UserActivity;
import com.leon.biuvideo.ui.adapters.video.MediaInfoRecommendAdapter;
import com.leon.biuvideo.ui.adapters.video.VideoEpisodeAdapter;
import com.leon.biuvideo.utils.PreferenceUtils;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/04
 * @Desc
 */
public class MediaVideoInfoFragment extends BaseFragment<FragmentMediaVideoInfoBinding> {
    private final VideoDetail.Data data;

    public MediaVideoInfoFragment(VideoDetail.Data data) {
        this.data = data;
    }

    @Override
    public FragmentMediaVideoInfoBinding getViewBinding() {
        return FragmentMediaVideoInfoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        VideoPlayerModel videoPlayerModel = new ViewModelProvider(ViewUtils.scanForActivity(context)).get(VideoPlayerModel.class);

        binding.face.setOnClickListener(v -> startActivity(UserActivity.class, Map.of(UserActivity.PARAM, data.getCard().getCard().getMid())));
        ViewUtils.setImg(context, binding.face, data.getCard().getCard().getFace());
        int roleVerify = data.getCard().getCard().getOfficial().getRole();
        if (roleVerify != 0) {
            binding.verify.setVisibility(View.VISIBLE);
            binding.verify.setImageResource(roleVerify == 1 ? R.drawable.ic_person_verify : R.drawable.ic_official_verify);
        }

        binding.author.setText(data.getCard().getCard().getName());
        if (data.getCard().getCard().getVip().getStatus() != 0) {
            binding.author.setTextColor(context.getColor(R.color.pink));
        }
        binding.fans.setText(String.format("%s粉丝", ValueUtils.generateCN(data.getCard().getFollower())));
        binding.works.setText(String.format("%s视频", ValueUtils.generateCN(data.getCard().getArchiveCount())));

        binding.follow.setText(data.getCard().getFollowing() ? "已关注" : "关注");
        binding.follow.setOnClickListener(v -> follow());

        binding.infoTitle.setContent(data.getView().getTitle());
        binding.view.setText(ValueUtils.generateCN(data.getView().getStat().getView()));
        binding.danmaku.setText(ValueUtils.generateCN(data.getView().getStat().getDanmaku()));
        binding.time.setText(ValueUtils.generateTime(data.getView().getPubdate(), "yyyy-MM-dd HH:mm", true));

        if (!"".equals(data.getView().getDesc())) {
            binding.desc.setVisibility(View.VISIBLE);
            binding.desc.setContent(data.getView().getDesc());
        }

        binding.like.setOnClickListener(v -> like());
        binding.coin.setOnClickListener(v -> coin());
        binding.favorite.setOnClickListener(v -> favorite());
        binding.share.setOnClickListener(v -> share());

        binding.likeStr.setText(ValueUtils.generateCN(data.getView().getStat().getLike()));
        binding.coinStr.setText(ValueUtils.generateCN(data.getView().getStat().getCoin()));
        binding.favoriteStr.setText(ValueUtils.generateCN(data.getView().getStat().getFavorite()));
        binding.shareStr.setText(ValueUtils.generateCN(data.getView().getStat().getShare()));

        if (PreferenceUtils.getLoginStatus(context)) {
            new ApiHelper<>(new RetrofitClient(BaseUrl.API, context)
                    .getHttpApi()
                    .getVideoRelation(data.getView().getBvid()))
                    .setOnResult(videoRelation -> {
                        binding.likeImg.setSelected(videoRelation.getData().getLike());
                        binding.coinImg.setSelected(videoRelation.getData().getCoin() > 0);
                        binding.favoriteImg.setSelected(videoRelation.getData().getFavorite());
                    }).doIt();
        }

        MediaInfoRecommendAdapter recommendAdapter = new MediaInfoRecommendAdapter(context);
        recommendAdapter.appendHead(data.getRelated());
        ViewUtils.linkAdapter(binding.recommend, recommendAdapter);

        if (!data.getView().getPages().isEmpty() && data.getView().getPages().size() > 1) {
            binding.episode.setVisibility(View.VISIBLE);

            int unselected = context.getColor(R.color.infoColor);
            int selected = context.getColor(R.color.blue);

            List<VideoDetail.Data.View.Page> pageList = data.getView().getPages();
            pageList.forEach(page -> page.setItemState(new VideoDetail.Data.View.Page.ItemState(unselected, false)));
            VideoDetail.Data.View.Page.ItemState state = pageList.get(0).getItemState();
            state.setSelected(true);
            state.setItemColor(selected);

            videoPlayerModel.getVideoTitleDisplay().setValue(pageList.get(0).getPart());

            VideoEpisodeAdapter adapter = new VideoEpisodeAdapter(context);
            adapter.setOnSelectedListener(integer -> {
                VideoDetail.Data.View.Page.ItemState itemState = pageList.get(integer).getItemState();
                itemState.setItemColor(unselected);
                itemState.setSelected(false);

                adapter.notifyItemChanged(integer);
            });
            adapter.appendHead(pageList);

            ViewUtils.listInitializer(binding.episode, adapter);
        } else {
            // 如果该视频没有选集，则播放器标题显示视频主标题；否则显示选集标题
            new ViewModelProvider(ViewUtils.scanForActivity(context)).get(VideoPlayerModel.class)
                    .getVideoTitleDisplay().setValue(data.getView().getTitle());
        }
    }

    private void share() {
        Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show();
    }

    private void favorite() {
        Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show();
    }

    private void coin() {
        Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show();
    }

    private void like() {
        Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show();
    }

    private void follow() {
        Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show();
    }
}
