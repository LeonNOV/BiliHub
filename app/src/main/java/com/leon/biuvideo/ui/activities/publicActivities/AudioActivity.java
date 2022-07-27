package com.leon.biuvideo.ui.activities.publicActivities;

import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.leon.biuvideo.base.baseActivity.AsyncHttpActivity;
import com.leon.biuvideo.beans.publicBeans.resources.audio.AudioInfo;
import com.leon.biuvideo.databinding.ActivityAudioBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.RequestData;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc 音频播放界面
 */
public class AudioActivity extends AsyncHttpActivity<ActivityAudioBinding, AudioInfo> {
    public static final String PARAM = "sid";
    private String sid;

    @Override
    public ActivityAudioBinding getViewBinding() {
        return ActivityAudioBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        this.sid = params.getString(PARAM);
        binding.back.setOnClickListener(v -> backPressed());
        binding.like.setOnClickListener(v -> Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected RequestData setRequestData() {
        return new RequestData(BaseUrl.MAIN);
    }

    @Override
    protected Observable<AudioInfo> createObservable(RetrofitClient retrofitClient) {
        return retrofitClient.getHttpApi().getAudioInfo(sid);
    }

    @Override
    protected void async(ApiHelper<AudioInfo> apiHelper) {
        apiHelper.setOnResult(audioInfo -> {
            binding.mv.setOnClickListener(v -> {
                if ("".equals(audioInfo.getData().getBvid())) {
                    Toast.makeText(context, "无对应视频", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM, audioInfo.getData().getBvid()));
                }
            });

            Glide
                    .with(context).load(audioInfo.getData().getCover())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3))).into(binding.audioBg);
            ViewUtils.setImg(context, binding.cover, audioInfo.getData().getCover());

            binding.title.setText(audioInfo.getData().getTitle());
            binding.author.setText(audioInfo.getData().getAuthor());
            binding.length.setText(ValueUtils.lengthGenerate(audioInfo.getData().getDuration()));
            binding.download.setOnClickListener(v -> {});
        }).doIt();
    }

    private void getAudioResourcesUrl() {
//        new ApiHelper<>(new RetrofitClient(BaseUrl.MAIN).getHttpApi().getAudioResources(sid))
//                .setOnResult(audioResources -> {
//
//                }).doIt();
    }
}