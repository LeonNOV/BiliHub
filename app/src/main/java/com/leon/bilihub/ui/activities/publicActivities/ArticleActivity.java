package com.leon.bilihub.ui.activities.publicActivities;

import android.util.Base64;
import android.util.Log;
import android.webkit.WebSettings;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.leon.bilihub.R;
import com.leon.bilihub.base.baseActivity.AsyncResponseBodyActivity;
import com.leon.bilihub.beans.publicBeans.resources.article.ArticleAuth;
import com.leon.bilihub.databinding.ActivityArticleBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RequestData;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.utils.FileUtils;
import com.leon.bilihub.utils.PreferenceUtils;
import com.leon.bilihub.utils.ViewUtils;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;

/**
 * @Author Leon
 * @Time 2022/6/18
 * @Desc
 */
public class ArticleActivity extends AsyncResponseBodyActivity<ActivityArticleBinding, ResponseBody> {
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
        binding.appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) ->
                binding.back.setColorFilter(Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange() ?
                        ContextCompat.getColor(context, R.color.primaryContrary) :
                        ContextCompat.getColor(context, R.color.primary)));
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
        return new RequestData(BaseUrl.MAIN);
    }

    @Override
    protected Observable<ResponseBody> createObservable(RetrofitClient retrofitClient) {
        return retrofitClient.getHttpRaw().getArticleRaw(articleId);
    }

    @Override
    protected void onAsyncResult(ResponseBody responseBody) {
        try {
            Document document = Jsoup.parse(responseBody.string());
            ArticleAuth articleAuth = null;

            Elements elements = document.select("script");
            for (Element element : elements) {
                if (element.html().startsWith("window.__INITIAL_STATE__")) {
                    String scriptContent = element.html();
                    String[] parts = scriptContent.split("window.__INITIAL_STATE__=");
                    if (parts.length >= 2) {
                        String initialState = parts[1].split(";")[0];
                        articleAuth = new Gson().fromJson(initialState, ArticleAuth.class);
                    }
                    break;
                }
            }

            if (articleAuth != null) {
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

                if ("".equals(articleAuth.getReadInfo().getBannerUrl())) {
                    ViewUtils.setImg(context, binding.banner, articleAuth.getReadInfo().getImageUrls().get(0));
                } else {
                    ViewUtils.setImg(context, binding.banner, articleAuth.getReadInfo().getBannerUrl());
                }

                binding.title.setText(articleAuth.getReadInfo().getTitle());

                String mid = articleAuth.getReadInfo().getAuthor().getMid();
                binding.face.setOnClickListener(v -> startActivity(UserActivity.class, Map.of(UserActivity.PARAM, mid)));
                ViewUtils.setImg(context, binding.face, articleAuth.getReadInfo().getAuthor().getFace());
                binding.author.setText(articleAuth.getReadInfo().getAuthor().getName());
                getArticleContent(document);
            } else {
                Toast.makeText(context, "数据获取失败", Toast.LENGTH_SHORT).show();
                backPressed();
            }
        } catch (IOException e) {
            Toast.makeText(context, "数据获取失败", Toast.LENGTH_SHORT).show();
            backPressed();
        }
    }

    /**
     * 获取文章主体内容
     */
    private void getArticleContent(Document document) {
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

            webPage
                    .append(head)
//                    .append(String.format("<style type=\"text/css\">%s</style>", FileUtils.getAssetsContent(context, "index.css")))
                    .append(String.format("<style type=\"text/css\">%s</style>", FileUtils.getAssetsContent(context, "read-mobile-a.css")))
                    .append(String.format("<style type=\"text/css\">%s</style>", FileUtils.getAssetsContent(context, "read-mobile-b.css")))
                    .append("</head><body>")
                    .append(html)
                    .append("<p/></body>\n</html>");

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