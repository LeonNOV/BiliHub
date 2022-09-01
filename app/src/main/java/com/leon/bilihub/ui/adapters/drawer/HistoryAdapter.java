package com.leon.bilihub.ui.adapters.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.viewbinding.ViewBinding;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.MultipleViewBindingAdapter;
import com.leon.bilihub.beans.account.History;
import com.leon.bilihub.databinding.ItemHistoryArticleBinding;
import com.leon.bilihub.databinding.ItemHistoryLiveBinding;
import com.leon.bilihub.databinding.ItemHistoryPgcBinding;
import com.leon.bilihub.databinding.ItemHistoryVideoBinding;
import com.leon.bilihub.ui.activities.publicActivities.ArticleActivity;
import com.leon.bilihub.ui.activities.publicActivities.LiveStreamActivity;
import com.leon.bilihub.ui.activities.publicActivities.VideoActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Locale;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/26
 * @Desc
 */
public class HistoryAdapter extends MultipleViewBindingAdapter<History.Data.Data> {
    private static final int HISTORY_VIDEO = R.layout.item_history_video;
    private static final int HISTORY_PGC = R.layout.item_history_pgc;
    private static final int HISTORY_LIVE = R.layout.item_history_live;
    private static final int HISTORY_ARTICLE = R.layout.item_history_article;

    public HistoryAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemViewType(History.Data.Data data) {
        switch (data.getHistory().getBusiness()) {
            case "archive":
                return HISTORY_VIDEO;
            case "pgc":
                return HISTORY_PGC;
            case "live":
                return HISTORY_LIVE;
            case "article-list":
            case "article":
                return HISTORY_ARTICLE;
            default:
                return 0;
        }
    }

    @Override
    protected ViewBinding getItemViewBinding(Context context, ViewGroup parent, int viewType) {
        switch (viewType) {
            case HISTORY_VIDEO:
                return ItemHistoryVideoBinding.bind(LayoutInflater.from(context).inflate(HISTORY_VIDEO, parent, false));
            case HISTORY_PGC:
                return ItemHistoryPgcBinding.bind(LayoutInflater.from(context).inflate(HISTORY_PGC, parent, false));
            case HISTORY_LIVE:
                return ItemHistoryLiveBinding.bind(LayoutInflater.from(context).inflate(HISTORY_LIVE, parent, false));
            case HISTORY_ARTICLE:
                return ItemHistoryArticleBinding.bind(LayoutInflater.from(context).inflate(HISTORY_ARTICLE, parent, false));
            default:
                return null;
        }
    }

    @Override
    protected void onBindViewHolder(History.Data.Data data, ViewBinding binding, int position) {
        switch (data.getHistory().getBusiness()) {
            case "archive":
                binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_VIDEO,
                        VideoActivity.PARAM_ID, data.getHistory().getBvid())));

                setArchive(data, (ItemHistoryVideoBinding) binding);
                break;
            case "pgc":
                String[] splitA = data.getUri().split("/");
                String seasonId = splitA[splitA.length - 1].replaceAll("ss", "");

                binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_PGC,
                        VideoActivity.PARAM_ID, seasonId)));
                setPgc(data, (ItemHistoryPgcBinding) binding);
                break;
            case "live":
                String[] splitB = data.getUri().split("/");
                String roomId = splitB[splitB.length - 1];
                binding.getRoot().setOnClickListener(v -> {
                    if (data.getLiveStatus() == 1) {
                        startActivity(LiveStreamActivity.class, Map.of(LiveStreamActivity.PARAM, roomId));
                    } else {
                        Toast.makeText(context, "该直播间未开播哟~", Toast.LENGTH_SHORT).show();
                    }
                });

                setLive(data, (ItemHistoryLiveBinding) binding);
                break;
            case "article-list":
            case "article":
                binding.getRoot().setOnClickListener(v -> startActivity(ArticleActivity.class, Map.of(ArticleActivity.PARAM, data.getHistory().getOid())));

                setArticle(data, (ItemHistoryArticleBinding) binding);
                break;
            default:
                break;
        }
    }

    private void setArchive(History.Data.Data data, ItemHistoryVideoBinding binding) {
        ViewUtils.setImg(context, binding.cover, data.getCover());
        binding.duration.setText(data.getProgress() == -1 ? "已看完" : data.getProgress() == 0 ? "未看完" :
                String.format(Locale.CHINESE, "%s / %s", ValueUtils.toMediaDuration(data.getProgress()), ValueUtils.toMediaDuration(data.getDuration())));
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getAuthorName());
        binding.time.setText(ValueUtils.generateTime(data.getViewAt(), "yyyy-MM-dd", true));
    }

    private void setPgc(History.Data.Data data, ItemHistoryPgcBinding binding) {
        ViewUtils.setImg(context, binding.cover, data.getCover());
        binding.duration.setText(data.getProgress() == -1 ? "已看完" : data.getProgress() == 0 ? "未看完" :
                String.format(Locale.CHINESE, "%s / %s", ValueUtils.toMediaDuration(data.getProgress()), ValueUtils.toMediaDuration(data.getDuration())));
        binding.title.setText(data.getTitle());
        binding.progressStr.setText(String.format(Locale.CHINESE, "看到%s", data.getShowTitle()));
    }

    private void setLive(History.Data.Data data, ItemHistoryLiveBinding binding) {
        ViewUtils.setImg(context, binding.cover, data.getCover());
        binding.liveStatus.setText(data.getBadge());
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getAuthorName());
    }

    private void setArticle(History.Data.Data data, ItemHistoryArticleBinding binding) {
        binding.title.setText(data.getTitle());
        ViewUtils.setImg(context, binding.cover, data.getCovers().get(0));
        binding.author.setText(data.getAuthorName());
        binding.time.setText(ValueUtils.generateTime(data.getViewAt(), "yyyy-MM-dd", true));
    }
}
