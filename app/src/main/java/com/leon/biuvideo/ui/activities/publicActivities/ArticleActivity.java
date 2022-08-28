package com.leon.biuvideo.ui.activities.publicActivities;

import android.util.Base64;
import android.webkit.WebSettings;

import com.leon.biuvideo.base.baseActivity.AsyncHttpActivity;
import com.leon.biuvideo.beans.publicBeans.resources.article.ArticleInfo;
import com.leon.biuvideo.databinding.ActivityArticleBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RequestData;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.utils.FileUtils;
import com.leon.biuvideo.utils.PreferenceUtils;
import com.leon.biuvideo.utils.ViewUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;

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
        WebSettings settings = binding.content.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDefaultFontSize(20);
    }

    @Override
    protected RequestData setRequestData() {
        return new RequestData(BaseUrl.API, Map.of(HttpApi.COOKIE, PreferenceUtils.getCookie(context)));
    }

    @Override
    protected Observable<ArticleInfo> createObservable(RetrofitClient retrofitClient) {
        return retrofitClient.getHttpApi().getArticleInfo(articleId);
    }

    @Override
    protected void onAsyncResult(ArticleInfo articleInfo) {
        if ("".equals(articleInfo.getData().getBannerUrl())) {
            ViewUtils.setImg(context, binding.banner, articleInfo.getData().getImageUrls().get(0));
        } else {
            ViewUtils.setImg(context, binding.banner, articleInfo.getData().getBannerUrl());
        }
        binding.title.setText(articleInfo.getData().getTitle());
        binding.face.setOnClickListener(v -> startActivity(UserActivity.class, Map.of(UserActivity.PARAM, articleInfo.getData().getMid())));

        getAuthorInfo(articleInfo.getData().getMid());
        getArticleContent();
    }

    /**
     * 获取文章作者信息
     *
     * @param uid   UID
     */
    private void getAuthorInfo(String uid) {
        new ApiHelper<>(new RetrofitClient(BaseUrl.API, context).getHttpApi().getUserInfo(uid))
                .setOnResult(userInfo -> {
                    ViewUtils.setImg(context, binding.face, userInfo.getData().getFace());
                    binding.author.setText(userInfo.getData().getName());
                })
                .doIt();
    }

    /**
     * 获取文章主体内容
     */
    private void getArticleContent() {
        new ApiHelper<>(new RetrofitClient(BaseUrl.MAIN).getHttpRaw().getArticleRaw(articleId))
                .setOnResult(responseBody -> {
                    Document document = Jsoup.parse(responseBody.string());
                    Element articleHolder = document.getElementById("read-article-holder");
                    Elements figures = articleHolder.getElementsByTag("figure");
                    for (Element figure : figures) {
                        Element img = figure.child(0);
                        Attributes attributes = img.attributes();

                        //修改图片的链接
                        attributes.put("src", "https:" + attributes.get("data-src"));
                        attributes.remove("data-src");
                    }

                    String unEncodedHtml = combine(articleHolder.html());

                    // 原HTML是未进行编码的，如果android版本为8.0以上（不包括8.0）,则需要将其解码为base64
                    String encodedHtml = Base64.encodeToString(unEncodedHtml.getBytes(), Base64.NO_PADDING);
                    binding.content.loadData(encodedHtml, "text/html", "base64");

                })
                .doIt();
    }

    private String combine(String html) {

        if (html != null) {
            StringBuilder webPage = new StringBuilder();

            String head = "<!DOCTYPE html>\n" +
                    "<html lang=\"zh\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 ,user-scalable=no\">\n" +
                    "<div id=\"read-article-holder\" class=\"normal-article-holder read-article-holder dark-theme\">";

            String css = "<style type=\"text/css\">" + FileUtils.getAssetsContent(context, "index.css") + "</style>";
            webPage.append(head).append(css).append("</head><body>");
            webPage.append(html).append("<p/>").append("</body>\n</html>");

            return webPage.toString();
        }

        return null;
    }

    @Override
    protected void onDestroy() {
        binding.content.clearCache(false);

        super.onDestroy();
    }
}