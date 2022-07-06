package com.leon.biuvideo.ui.activities;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityLoginBinding;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2021/11/3
 * @Desc
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements View.OnClickListener {
    private static final String ORIGINAL_URL = "https://passport.bilibili.com/login?gourl=https://m.bilibili.com/index.html";
    private static final int PROGRESS_MAX = 100;

    private Handler handler;

    @Override
    public ActivityLoginBinding getViewBinding() {
        return ActivityLoginBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        binding.activityLoginBack.setOnClickListener(this);
        binding.activityLoginRefresh.setOnClickListener(this);

        initWebView();
    }

    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    private void initWebView() {
        WebSettings webViewSettings = binding.activityLoginWebView.getSettings();
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setAllowFileAccess(false);

        //是否可以缩放，默认true
        webViewSettings.setSupportZoom(true);
        webViewSettings.setBuiltInZoomControls(true);

        //是否使用缓存
        webViewSettings.setAppCacheEnabled(true);

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
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                //判断加载完成的页面是否为B站手机版主页
                if ("https://m.bilibili.com/index.html".equals(url)) {
//                    SimpleSnackBar.make(view, "正在获取用户信息中，请不要进行任何操作", SimpleSnackBar.LENGTH_LONG).show();
                    CookieManager cookieManager = CookieManager.getInstance();
                    String cookieStr = cookieManager.getCookie(url).trim();

                    //获取用户ID
                    String[] split = cookieStr.split("; ");
                    Map<String, String> cookieMap = new HashMap<>();
                    for (String s : split) {
                        if (s.startsWith("DedeUserID")) {
                            String[] arrayTemp = s.split("=");
                            cookieMap.put(arrayTemp[0], arrayTemp[1]);
                        }
                    }

                    if (!cookieMap.containsKey("DedeUserID")) {
//                        SimpleSnackBar.make(view, "获取不到登录信息，请重新进行登录", SimpleSnackBar.LENGTH_SHORT).show();
                        return;
                    }

                    // TODO 将用户信息添加至配置文件
//                    PreferenceUtils.setCookie(cookieStr);
//                    PreferenceUtils.setUserId(cookieMap.get("DedeUserID"));
//                    PreferenceUtils.setLoginStatus(true);

                    // 登录成功，发送广播
                    sendBroadcast();

                    backPressed();
                }
            }
        });

        binding.activityLoginRefresh.setOnTouchListener((v, event) -> ViewUtils.Zoom(event, binding.activityLoginRefresh));
        binding.activityLoginBack.setOnTouchListener((v, event) -> ViewUtils.Zoom(event, binding.activityLoginBack));

        binding.activityLoginBack.setOnClickListener(v -> backPressed());
        binding.activityLoginRefresh.setOnClickListener(v -> binding.activityLoginWebView.reload());

        // 参数gourl为登陆成功后跳转到的url地址
        binding.activityLoginWebView.loadUrl(ORIGINAL_URL);
    }

    /**
     * 登录成功后发送本地广播
     */
    private void sendBroadcast() {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);

//        Intent intent = new Intent(Actions.LOGIN_SUCCESS);
//        intent.putExtra("loginStatus", true);

//        localBroadcastManager.sendBroadcast(intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.activity_login_back) {

        } else if (id == R.id.activity_login_refresh) {
            binding.activityLoginWebView.reload();
        }
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