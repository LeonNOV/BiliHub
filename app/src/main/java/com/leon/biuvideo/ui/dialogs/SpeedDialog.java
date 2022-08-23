package com.leon.biuvideo.ui.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import androidx.lifecycle.ViewModelProvider;

import com.leon.biuvideo.R;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoSpeed;
import com.leon.biuvideo.databinding.DialogVideoParameterBinding;
import com.leon.biuvideo.model.VideoPlayerModel;
import com.leon.biuvideo.ui.adapters.video.VideoSpeedAdapter;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class SpeedDialog extends AppCompatDialog {
    private final static float MAX_SPEED = 2.0F;
    private final static float MIN_SPEED = 0.5F;
    private final static float STEP_SPEED = 0.25F;

    private VideoPlayerModel videoPlayerModel;

    /**
     * bvid/seasonId or whatever
     */
    private final List<VideoSpeed> videoSpeedList = new ArrayList<>();

    public SpeedDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DialogVideoParameterBinding binding = DialogVideoParameterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        if (window != null) {
            window.setGravity(Gravity.END);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(getContext().getResources().getDimensionPixelSize(R.dimen.dialog_width), WindowManager.LayoutParams.MATCH_PARENT);
            window.setDimAmount(0f);
        }

        setCancelable(true);

        videoPlayerModel = new ViewModelProvider(ViewUtils.scanForActivity(getContext())).get(VideoPlayerModel.class);

        init(binding);
    }

    private void init(DialogVideoParameterBinding binding) {
        Float value = videoPlayerModel.getVideoSpeed().getValue();
        int initSelectedPosition = 0;
        int index = 0;
        for (float speed = MAX_SPEED; speed >= MIN_SPEED; speed -= STEP_SPEED) {
            if (value == speed) {
                videoSpeedList.add(new VideoSpeed(speed, speed + "x", true));
                initSelectedPosition = index;
            } else {
                videoSpeedList.add(new VideoSpeed(speed, speed + "x", false));
            }

            ++ index;
        }

        VideoSpeedAdapter adapter = new VideoSpeedAdapter(getContext(), initSelectedPosition);
        adapter.setOnSelectedListener(videoSpeedWrap -> {
            videoPlayerModel.getVideoSpeed().setValue(videoSpeedWrap.getSpeed());
            videoSpeedList.get(videoSpeedWrap.getPosition()).setSelected(false);

            adapter.notifyItemChanged(videoSpeedWrap.getPosition());
        });
        adapter.appendHead(videoSpeedList);

        ViewUtils.linkAdapter(binding.content, adapter);
    }
}
