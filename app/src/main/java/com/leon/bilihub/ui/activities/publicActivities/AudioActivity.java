package com.leon.bilihub.ui.activities.publicActivities;

import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.leon.bilihub.base.baseActivity.AsyncHttpActivity;
import com.leon.bilihub.beans.publicBeans.resources.audio.AudioInfo;
import com.leon.bilihub.databinding.ActivityAudioBinding;
import com.leon.bilihub.http.ApiHelper;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.RequestData;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.model.AudioProgressModel;
import com.leon.bilihub.utils.AudioController;
import com.leon.bilihub.utils.ViewUtils;
import com.leon.bilihub.wraps.AudioProgressWrap;
import com.squareup.picasso.Picasso;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc 音频播放界面
 */
public class AudioActivity extends AsyncHttpActivity<ActivityAudioBinding, AudioInfo> implements AudioController.AudioControllerCallback {
    public static final String PARAM = "sid";
    private String sid;
    private AudioController audioController;

    private AudioProgressModel progressModel;
    private Observer<AudioProgressWrap> audioProgressObserver;

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
        binding.download.setOnClickListener(v -> Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show());

        progressModel = new ViewModelProvider(this).get(AudioProgressModel.class);
        audioProgressObserver = audioProgress -> {
            if (audioProgress.getDuration() > 0) {
                binding.seekBar.setEnabled(true);

                int videoPos = (int) (audioProgress.getCurrentPosition() * 1.0 / audioProgress.getDuration() * binding.seekBar.getMax());

                binding.seekBar.setProgress(videoPos);
            } else {
                binding.seekBar.setEnabled(false);
            }

            if (audioProgress.getBufferPosition() >= 95) {
                binding.seekBar.setSecondaryProgress(binding.seekBar.getMax());
            } else {
                binding.seekBar.setSecondaryProgress(audioProgress.getBufferPosition() * 10);
            }

            binding.length.setText(toLengthStr(audioProgress.getDuration()));
            binding.progress.setText(toLengthStr(audioProgress.getCurrentPosition()));
        };
        progressModel.getAudioProgress().observeForever(audioProgressObserver);
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
    protected void onAsyncResult(AudioInfo audioInfo) {
        binding.mv.setOnClickListener(v -> {
            if ("".equals(audioInfo.getData().getBvid())) {
                Toast.makeText(context, "无对应视频", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_VIDEO,
                        VideoActivity.PARAM_ID, audioInfo.getData().getBvid()));
            }
        });

        Picasso
                .get()
                .load(audioInfo.getData().getCover())
                .transform(new com.leon.bilihub.ui.widget.BlurTransformation(context, 25))
                .priority(Picasso.Priority.HIGH)
                .into(binding.audioBg);

        ViewUtils.setImg(context, binding.cover, audioInfo.getData().getCover());

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
    }

    /**
     * 获取音频链接，并实例化{@link AudioController}
     */
    private void getAudioResourcesUrl() {
        new ApiHelper<>(new RetrofitClient(BaseUrl.MAIN, context).getHttpApi().getAudioResources(sid))
                .setOnResult(audioResources -> {
                    audioController = new AudioController(context);
                    audioController.setAudioControllerCallback(AudioActivity.this);
                    audioController.setUrl(audioResources.getData().getCdns().get(0));
                    audioController.setPlayerStat();
                }).doIt();
    }

    @Override
    public void finished() {
        binding.control.setSelected(false);
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
    protected void onDestroy() {
        progressModel.getAudioProgress().removeObserver(audioProgressObserver);
        audioController.release();

        super.onDestroy();
    }
}