package com.leon.biuvideo.ui.activities.publicActivities;

import android.widget.Toast;

import com.leon.biuvideo.base.baseActivity.AsyncHttpActivity;
import com.leon.biuvideo.beans.publicBeans.resources.audio.AudioInfo;
import com.leon.biuvideo.databinding.ActivityAudioBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.RequestData;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.utils.AudioController;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc 音频播放界面
 * todo 待完善
 */
public class AudioActivity extends AsyncHttpActivity<ActivityAudioBinding, AudioInfo> implements AudioController.AudioControllerCallback {
    public static final String PARAM = "sid";
    private String sid;
    private AudioController audioController;

    @Override
    public ActivityAudioBinding getViewBinding() {
        return ActivityAudioBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        this.sid = params.getString(PARAM);
        binding.back.setOnClickListener(v -> backPressed());
        binding.like.setOnClickListener(v -> Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show());
        binding.control.setOnClickListener(v -> audioController.setPlayerStat());
        binding.download.setOnClickListener(v -> Toast.makeText(context, "download", Toast.LENGTH_SHORT).show());
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
                    startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_BVID, audioInfo.getData().getBvid()));
                }
            });

//            Glide
//                    .with(context).load(audioInfo.getData().getCover())
//                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3))).into(binding.audioBg);
//            ViewUtils.setImg(context, binding.cover, audioInfo.getData().getCover());

            binding.title.setText(audioInfo.getData().getTitle());
            binding.author.setText(audioInfo.getData().getAuthor());
            binding.length.setText(toLengthStr(audioInfo.getData().getDuration()));

            binding.seekBar.setOnSeekBarChangeListener((AudioController.SimpleOnSeekBarChangeListener) (seekBar, progress, fromUser) -> {
                if (!fromUser) {
                    return;
                }

                long duration = audioController.audioDuration;
                long newPosition = (duration * progress) / binding.seekBar.getMax();
                audioController.seekTo(newPosition);
            });

            getAudioResourcesUrl();
        }).doIt();
    }

    /**
     * 获取音频链接，并实例化{@link AudioController}
     */
    private void getAudioResourcesUrl() {
        new ApiHelper<>(new RetrofitClient(BaseUrl.MAIN).getHttpApi().getAudioResources(sid))
                .setOnResult(audioResources -> {
                    audioController = new AudioController(context);
                    audioController.setUrl(audioResources.getData().getCdns().get(0));
                    audioController.setPlayerStat();
                    audioController.setAudioControllerCallback(AudioActivity.this);
                }).doIt();
    }

    @Override
    public void finished() {
        binding.control.setSelected(false);
    }

    @Override
    public void progress(int currentPosition, int bufferPosition, int duration) {
        if (duration > 0) {
            binding.seekBar.setEnabled(true);

            int videoPos = (int) (currentPosition * 1.0 / duration * binding.seekBar.getMax());

            binding.seekBar.setProgress(videoPos);
        } else {
            binding.seekBar.setEnabled(false);
        }

        if (bufferPosition >= 95) {
            binding.seekBar.setSecondaryProgress(binding.seekBar.getMax());
        } else {
            binding.seekBar.setSecondaryProgress(bufferPosition * 10);
        }

        binding.length.setText(toLengthStr(duration));
        binding.progress.setText(toLengthStr(currentPosition));
    }

    @Override
    public void playStat(int stat) {
        switch (stat) {
            case AudioController.PLAYING:
                binding.control.setSelected(true);

                break;
            case AudioController.FINISHED:
            case AudioController.PAUSED:
                binding.control.setSelected(false);

                break;
            default:
                break;
        }
    }

    /**
     * 进度转换
     */
    private String toLengthStr(int value) {
        int minute = value / 1000 / 60;
        int second = value / 1000 % 60;
        return (minute < 10 ? "0" + minute : minute + "") + ":" + (second < 10 ? "0" + second : second + "");
    }

    @Override
    public void onBackPressed() {
        audioController.release();
        super.onBackPressed();
    }
}