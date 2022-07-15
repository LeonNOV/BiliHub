package com.leon.biuvideo.ui.fragments.userFragments;

import android.annotation.SuppressLint;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.publicBeans.user.UserVideo;
import com.leon.biuvideo.databinding.FragmentUserMediaBinding;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.activities.publicActivities.UserActivity;
import com.leon.biuvideo.ui.activities.publicActivities.UserAudioActivity;
import com.leon.biuvideo.ui.adapters.user.UserVideoAdapter;
import com.leon.biuvideo.utils.PaginationLoader;
import com.leon.biuvideo.utils.ViewUtils;

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
        binding.audio.setOnTouchListener((v, event) -> ViewUtils.Zoom(event, binding.audio));
        binding.audio.setOnClickListener(v -> startActivity(UserAudioActivity.class, Map.of(UserActivity.PARAM, mid)));

        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        loader = new PaginationLoader<>(RefreshContentBinding.bind(binding.content.getRoot()), new UserVideoAdapter(context));
        loader.closeRefresh();
        loader.setGuide(userVideo -> userVideo.getData().getList().getVideoList());
        loader.setUpdateInterface(loadType -> loader.setObservable(httpApi.getUserVideo(mid, ++pageNum, "pubdate")));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
