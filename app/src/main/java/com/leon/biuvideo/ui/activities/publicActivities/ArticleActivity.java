package com.leon.biuvideo.ui.activities.publicActivities;

import com.leon.biuvideo.base.baseActivity.AsyncHttpActivity;
import com.leon.biuvideo.beans.publicBeans.resources.article.ArticleInfo;
import com.leon.biuvideo.databinding.ActivityArticleBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RequestData;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.http.TestValue;
import com.leon.biuvideo.ui.adapters.channel.ChannelFeaturedFilterAdapter;
import com.leon.biuvideo.ui.fragments.channelFragments.ChannelFeaturedFragment;
import com.leon.biuvideo.utils.ViewUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.reactivex.rxjava3.core.Observable;

/**
 * @Author Leon
 * @Time 2022/6/18
 * @Desc
 */
public class ArticleActivity extends AsyncHttpActivity<ActivityArticleBinding, ArticleInfo> {
    public static final String PARAM = "articleId";
    public String articleId;

    @Override
    public ActivityArticleBinding getViewBinding() {
        return ActivityArticleBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        this.articleId = params.getString(PARAM);
        binding.back.setOnClickListener(v -> backPressed());
    }

    @Override
    protected RequestData setRequestData() {
        return new RequestData(BaseUrl.API, Map.of(HttpApi.COOKIE, TestValue.TEST_COOKIE));
    }

    @Override
    protected Observable<ArticleInfo> createObservable(RetrofitClient retrofitClient) {
        return retrofitClient.getHttpApi().getArticleInfo(articleId);
    }

    @Override
    protected void async(ApiHelper<ArticleInfo> apiHelper) {
        apiHelper.setOnResult(articleInfo -> {
            ViewUtils.setImg(context, binding.banner, articleInfo.getData().getBannerUrl());
            binding.title.setText(articleInfo.getData().getTitle());
            getAuthorName(articleInfo.getData().getMid());
            getArticleContent();
        }).doIt();
    }

    private void getAuthorName(String uid) {
        new ApiHelper<>(new RetrofitClient(BaseUrl.API).getHttpApi().getUserInfo(uid))
                .setOnResult(userInfo -> ViewUtils.setImg(context, binding.face, userInfo.getData().getFace()))
                .doIt();
    }

    private void getArticleContent() {
        new ApiHelper<>(new RetrofitClient(BaseUrl.MAIN).getHttpRaw().getArticleRaw(articleId))
                .setOnResult(responseBody -> {

                })
                .doIt();
    }
}