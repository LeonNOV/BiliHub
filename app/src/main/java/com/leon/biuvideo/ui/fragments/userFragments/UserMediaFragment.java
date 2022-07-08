package com.leon.biuvideo.ui.fragments.userFragments;

import android.annotation.SuppressLint;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.publicBeans.user.UserVideo;
import com.leon.biuvideo.databinding.FragmentUserMediaBinding;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.UserVideoItemAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

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
        binding.content.refresh.container.container.setEnableRefresh(false);

        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        loader = new PaginationLoader<>(RefreshContentBinding.bind(binding.content.refresh.getRoot()), new UserVideoItemAdapter(context));
        loader.setGuide(userVideo -> userVideo.getData().getList().getVideoList());
        loader.setUpdateInterface(loadType -> loader.setObservable(httpApi.getUserVideo(mid, ++pageNum, "pubdate")));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
