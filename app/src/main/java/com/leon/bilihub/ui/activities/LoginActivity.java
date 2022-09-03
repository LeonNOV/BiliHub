package com.leon.bilihub.ui.activities;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.databinding.ActivityLoginBinding;
import com.leon.bilihub.utils.PreferenceUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2021/11/3
 * @Desc
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    private static final String LOGIN_URL = "https://passport.bilibili.com/login?gourl=https://m.bilibili.com/index.html";
    private static final String REDIRECT_URL = "https://m.bilibili.com/index.html";

    private static final int PROGRESS_MAX = 100;

    private Handler handler;

    @Override
    public ActivityLoginBinding getViewBinding() {
        return ActivityLoginBinding.inflate(getLayoutInflater());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void init() {
        binding.activityLoginBack.setOnTouchListener((v, event) -> ViewUtils.zoom(event, binding.activityLoginBack));
        binding.activityLoginBack.setOnClickListener(v -> backPressed());

        binding.activityLoginRefresh.setOnTouchListener((v, event) -> ViewUtils.zoom(event, binding.activityLoginRefresh));
        binding.activityLoginRefresh.setOnClickListener(v -> binding.activityLoginWebView.reload());

        binding.activityLoginWebView.loadUrl(LOGIN_URL);
        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        WebSettings webViewSettings = binding.activityLoginWebView.getSettings();
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setAllowFileAccess(false);

        //是否可以缩放，默认true
        webViewSettings.setSupportZoom(true);
        webViewSettings.setBuiltInZoomControls(true);

        //DOM Storage
        webViewSettings.setDomStorageEnabled(true);
        webViewSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        //网页自适应
        //将图片调整到适合webView的大小
        webViewSettings.setUseWideViewPort(true);

        //缩放至屏幕大小
        webViewSettings.setLoadWithOverviewMode(true);

        binding.activityLoginWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                binding.activityLoginProgress.setProgress(newProgress);
                if (newProgress == PROGRESS_MAX) {
                    //700毫秒后进度条消失
                    if (handler == null) {
                        handler = new Handler(Looper.getMainLooper());
                    }
                    handler.postDelayed(() -> binding.activityLoginProgress.setVisibility(View.GONE), 700);
                } else {
                    binding.activityLoginProgress.setVisibility(View.VISIBLE);
                }

                super.onProgressChanged(view, newProgress);
            }
        });

        binding.activityLoginWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //判断加载完成的页面是否为B站手机版主页
                if (REDIRECT_URL.equals(url)) {
                    CookieManager cookieManager = CookieManager.getInstance();
                    String cookieStr = cookieManager.getCookie(url).trim();

                    //获取用户ID
                    String[] split = cookieStr.split("; ");
                    Map<String, String> cookieMap = new HashMap<>(split.length);
                    for (String s : split) {
                        if (s.startsWith("DedeUserID")) {
                            String[] arrayTemp = s.split("=");
                            cookieMap.put(arrayTemp[0], arrayTemp[1]);
                        }
                    }

                    if (!cookieMap.containsKey("DedeUserID")) {
                        updateViewModel(false);
                        backPressed();
                    }

                    PreferenceUtils.setCookie(context, cookieStr);
                    PreferenceUtils.setUid(context, cookieMap.get("DedeUserID"));
                    PreferenceUtils.setLoginStatus(context, true);

                    updateViewModel(true);

                    backPressed();
                }

                super.onPageStarted(view, url, favicon);
            }
        });
    }

    /**
     * 登录成功后更新数据
     */
    private void updateViewModel(boolean loginStatus) {
        MainActivity.model.getLoginStatus().setValue(loginStatus);
    }

    @Override
    public void onDestroy() {
        //清除WebView缓存和Cookie
        binding.activityLoginWebView.clearHistory();
        binding.activityLoginWebView.clearFormData();
        binding.activityLoginWebView.clearCache(true);

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookies(null);
        cookieManager.removeSessionCookies(null);

        binding.activityLoginWebView.destroy();

        super.onDestroy();
    }
}