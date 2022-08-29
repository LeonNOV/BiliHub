package com.leon.bilihub.ui.activities.publicActivities;

import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.beans.publicBeans.user.UserAudio;
import com.leon.bilihub.databinding.ActivityUserAudioBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.user.UserAudioAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/07/08
 * @Desc
 */
public class UserAudioActivity extends BaseActivity<ActivityUserAudioBinding> {
    private int pageNum = 0;

    @Override
    public ActivityUserAudioBinding getViewBinding() {
        return ActivityUserAudioBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        String mid = params.getString(UserActivity.PARAM);

        HttpApi httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        PaginationLoader<UserAudio, UserAudio.Data.Audio> loader = new PaginationLoader<>(binding.content, new UserAudioAdapter(context));
        loader.setGuide(userAudio -> userAudio.getData().getAudioList());
        loader.setUpdateInterface(loadType -> httpApi.getUserAudio(mid, ++pageNum)).obtain();
    }
}
