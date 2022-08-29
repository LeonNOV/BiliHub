package com.leon.bilihub.ui.fragments.userFragments;

import android.annotation.SuppressLint;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.publicBeans.user.UserVideo;
import com.leon.bilihub.databinding.FragmentUserMediaBinding;
import com.leon.bilihub.databinding.RefreshContentBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.activities.publicActivities.UserActivity;
import com.leon.bilihub.ui.activities.publicActivities.UserAudioActivity;
import com.leon.bilihub.ui.adapters.user.UserVideoAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/07
 * @Desc
 */
public class UserMediaFragment extends BaseLazyFragment<FragmentUserMediaBinding> {
    private HttpApi httpApi;
    private PaginationLoader<UserVideo, UserVideo.Data.DataList.Video> loader;
    private final String mid;
    private int pageNum = 0;

    public UserMediaFragment(String mid) {
        this.mid = mid;
    }

    @Override
    public FragmentUserMediaBinding getViewBinding() {
        return FragmentUserMediaBinding.inflate(getLayoutInflater());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView() {
        binding.audio.setOnTouchListener((v, event) -> ViewUtils.zoom(event, binding.audio));
        binding.audio.setOnClickListener(v -> startActivity(UserAudioActivity.class, Map.of(UserActivity.PARAM, mid)));

        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        loader = new PaginationLoader<>(RefreshContentBinding.bind(binding.content.getRoot()), new UserVideoAdapter(context));
        loader.setGuide(userVideo -> userVideo.getData().getList().getVideoList());
        loader.setUpdateInterface(loadType -> httpApi.getUserVideo(mid, ++pageNum, "pubdate"));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
