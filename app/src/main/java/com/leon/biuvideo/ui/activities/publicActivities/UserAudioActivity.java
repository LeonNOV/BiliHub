package com.leon.biuvideo.ui.activities.publicActivities;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.publicBeans.user.UserAudio;
import com.leon.biuvideo.databinding.ActivityUserAudioBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.user.UserAudioAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

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
