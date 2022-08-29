package com.leon.bilihub.ui.dialogs;

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

import com.leon.bilihub.R;
import com.leon.bilihub.beans.publicBeans.resources.video.VideoQuality;
import com.leon.bilihub.databinding.DialogVideoParameterBinding;
import com.leon.bilihub.model.VideoPlayerModel;
import com.leon.bilihub.ui.adapters.video.VideoQualityAdapter;
import com.leon.bilihub.utils.ViewUtils;
import com.leon.bilihub.wraps.VideoResourceWrap;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class QualityDialog extends AppCompatDialog {
    private VideoQualityAdapter adapter;

    private VideoPlayerModel videoPlayerModel;
    private DialogVideoParameterBinding binding;

    public QualityDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DialogVideoParameterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        if (window != null) {
            window.setGravity(Gravity.END);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(getContext().getResources().getDimensionPixelSize(R.dimen.dialog_width), WindowManager.LayoutParams.MATCH_PARENT);
            window.setDimAmount(0f);
        }

        setCancelable(true);

        init();
    }

    private void init() {
        videoPlayerModel = new ViewModelProvider(ViewUtils.scanForActivity(getContext())).get(VideoPlayerModel.class);
        List<VideoQuality> qualities = videoPlayerModel.getVideoQualityListDisplay().getValue();

        adapter = new VideoQualityAdapter(getContext());
        adapter.setOnSelectedListener(videoQualityWrap -> {
            VideoResourceWrap resourceWrap = videoPlayerModel.getVideoResource().getValue();
            resourceWrap.setQuality(videoQualityWrap.getQuality());
            videoPlayerModel.getVideoResource().setValue(resourceWrap);

            qualities.get(videoQualityWrap.getPosition()).setSelected(false);

            adapter.notifyItemChanged(videoQualityWrap.getPosition());
        });
        adapter.appendHead(qualities);

        ViewUtils.linkAdapter(binding.content, adapter);
    }
}
