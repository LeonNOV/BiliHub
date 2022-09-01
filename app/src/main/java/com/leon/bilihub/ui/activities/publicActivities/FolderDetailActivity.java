package com.leon.bilihub.ui.activities.publicActivities;

import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.beans.account.CollectFolderDetail;
import com.leon.bilihub.beans.account.FolderDetailMedia;
import com.leon.bilihub.beans.account.FavoriteFolderDetail;
import com.leon.bilihub.databinding.ActivityFolderDetailBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.drawer.favFolder.FolderDetailMediaAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @Author Leon
 * @Time 2022/8/2
 * @Desc
 */
public class FolderDetailActivity extends BaseActivity<ActivityFolderDetailBinding> {
    public static final String PARAM_A = "seasonId";
    public static final String PARAM_B = "mediaId";

    private String seasonId;
    private String mediaId;

    private int collectCount = 0;
    private int addedCount = 0;

    private int pageNum = 0;
    private boolean folderInfoStatus;
    private HttpApi httpApi;

    @Override
    public ActivityFolderDetailBinding getViewBinding() {
        return ActivityFolderDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        this.seasonId = params.getString(PARAM_A, null);
        this.mediaId = params.getString(PARAM_B, null);

        initContent();
    }

    private void initContent() {
        httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
        if (seasonId != null) {
            new Col(new PaginationLoader<>(binding.content, new FolderDetailMediaAdapter(context)));
        } else if (mediaId != null) {
            new Fav(new PaginationLoader<>(binding.content, new FolderDetailMediaAdapter(context)));
        } else {
            backPressed();
        }
    }

    private List<FolderDetailMedia> favoriteFolderContent(FavoriteFolderDetail folderDetail) {
        if (!folderInfoStatus) {
            FavoriteFolderDetail.Data.Info info = folderDetail.getData().getInfo();
            contentSetter(new DetailContent(
                    info.getCover(),
                    info.getTitle(),
                    info.getUpper().getName(),
                    String.format(Locale.CHINESE, "%s个内容", ValueUtils.generateCN(info.getMediaCount())),
                    String.format(Locale.CHINESE, "%s播放", ValueUtils.generateCN(info.getCntInfo().getPlay()))
            ));

            folderInfoStatus = true;
        }

        List<FavoriteFolderDetail.Data.Media> medias = folderDetail.getData().getMedias();
        if (medias != null) {
            List<FolderDetailMedia> folderDetailMediaList = new ArrayList<>();
            for (FavoriteFolderDetail.Data.Media media : medias) {
                folderDetailMediaList.add(new FolderDetailMedia(media.getBvid(), media.getCover(), media.getDuration(), media.getTitle(),
                        media.getUpper().getName(), media.getCntInfo().getPlay(), media.getCntInfo().getCollect()));
            }
            return folderDetailMediaList;
        }
        return null;
    }

    private List<FolderDetailMedia> collectFolderContent(CollectFolderDetail folderDetail) {
        if (!folderInfoStatus) {
            CollectFolderDetail.Data.Info info = folderDetail.getData().getInfo();
            this.collectCount = info.getMediaCount();
            contentSetter(new DetailContent(
                    info.getCover(),
                    info.getTitle(),
                    info.getUpper().getName(),
                    String.format(Locale.CHINESE, "%s个内容", ValueUtils.generateCN(info.getMediaCount())),
                    String.format(Locale.CHINESE, "%s播放", ValueUtils.generateCN(info.getCntInfo().getPlay()))
            ));

            folderInfoStatus = true;
        }

        List<CollectFolderDetail.Data.Media> medias = folderDetail.getData().getMedias();
        if (medias != null && this.addedCount != this.collectCount) {
            this.addedCount += medias.size();

            List<FolderDetailMedia> folderDetailMediaList = new ArrayList<>();
            for (CollectFolderDetail.Data.Media media : medias) {
                folderDetailMediaList.add(new FolderDetailMedia(media.getBvid(), media.getCover(), media.getDuration(), media.getTitle(),
                        media.getUpper().getName(), media.getCntInfo().getPlay(), media.getCntInfo().getCollect()));
            }

            return folderDetailMediaList;
        }

        return null;
    }

    private void contentSetter(DetailContent detailContent) {
        binding.topBar.setTopBarTitle(detailContent.Title);
        ViewUtils.setImg(context, binding.cover, detailContent.Cover);
        binding.title.setText(detailContent.Title);
        binding.creator.setText(detailContent.Creator);
        binding.count.setText(detailContent.ContentCount);
        binding.play.setText(detailContent.Play);
    }

    private class Col {
        private Col(PaginationLoader<CollectFolderDetail, FolderDetailMedia> loader) {
            loader.setGuide(FolderDetailActivity.this::collectFolderContent);
            loader.setUpdateInterface(loadType -> FolderDetailActivity.this.httpApi.getCollectFolderDetail(seasonId, ++pageNum)).firstObtain();
        }
    }

    private class Fav {
        private Fav(PaginationLoader<FavoriteFolderDetail, FolderDetailMedia> loader) {
            loader.setGuide(FolderDetailActivity.this::favoriteFolderContent);
            loader.setUpdateInterface(loadType -> FolderDetailActivity.this.httpApi.getFavoriteFolderDetail(mediaId, ++pageNum)).firstObtain();
        }
    }

    private static class DetailContent {
        private final String Cover;
        private final String Title;
        private final String Creator;
        private final String ContentCount;
        private final String Play;

        public DetailContent(String cover, String title, String creator, String contentCount, String play) {
            Cover = cover;
            Title = title;
            Creator = creator;
            ContentCount = contentCount;
            Play = play;
        }
    }
}