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
import androidx.recyclerview.widget.RecyclerView;

import com.leon.biuvideo.R;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoSpeed;
import com.leon.biuvideo.databinding.DialogVideoParameterBinding;
import com.leon.biuvideo.databinding.ItemVideoSpeedBinding;
import com.leon.biuvideo.ui.adapters.video.VideoSpeedAdapter;
import com.leon.biuvideo.utils.ViewUtils;
import com.leon.biuvideo.model.VideoEpisodeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class SpeedDialog extends AppCompatDialog {
    private final static float MAX_SPEED = 2.0F;
    private final static float MIN_SPEED = 0.5F;
    private final static float STEP_SPEED = 0.25F;

    private VideoEpisodeModel videoEpisodeModel;

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

        videoEpisodeModel = new ViewModelProvider(ViewUtils.scanForActivity(getContext())).get(VideoEpisodeModel.class);

        init(binding);
    }

    private void init(DialogVideoParameterBinding binding) {
        for (float speed = MAX_SPEED; speed >= MIN_SPEED; speed -= STEP_SPEED) {
            videoSpeedList.add(new VideoSpeed(speed, speed + "x"));
        }

        int initialIndex = 0;
        Float selectedSpeed = videoEpisodeModel.getSpeed().getValue();
        for (int i = 0; i < videoSpeedList.size(); i++) {
            if (selectedSpeed == videoSpeedList.get(i).getSpeed()) {
                initialIndex = i;
                break;
            }
        }

        RecyclerView.LayoutManager layoutManager = binding.content.getLayoutManager();
        VideoSpeedAdapter adapter = new VideoSpeedAdapter(getContext(), initialIndex);
        adapter.setOnSelectedListener(videoSpeedWrap -> {
            videoEpisodeModel.getSpeed().setValue(videoSpeedWrap.getSpeed());
            ItemVideoSpeedBinding.bind(Objects.requireNonNull(layoutManager.getChildAt(videoSpeedWrap.getPrePosition())))
                    .speed.setTextColor(getContext().getColor(R.color.white));
        });
        adapter.appendHead(videoSpeedList);

        ViewUtils.linkAdapter(binding.content, adapter);
    }
}
