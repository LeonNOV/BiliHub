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
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoQuality;
import com.leon.biuvideo.databinding.DialogVideoParameterBinding;
import com.leon.biuvideo.databinding.ItemVideoQualityBinding;
import com.leon.biuvideo.http.Quality;
import com.leon.biuvideo.model.VideoEpisodeModel;
import com.leon.biuvideo.ui.adapters.video.VideoQualityAdapter;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.List;
import java.util.Objects;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class QualityDialog extends AppCompatDialog {
    private VideoQualityAdapter adapter;

    private VideoEpisodeModel videoEpisodeModel;

    public QualityDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoEpisodeModel = new ViewModelProvider(ViewUtils.scanForActivity(getContext())).get(VideoEpisodeModel.class);

        DialogVideoParameterBinding binding = DialogVideoParameterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<VideoQuality> qualities = videoEpisodeModel.getQualityList().getValue();
        int initialIndex = 0;
        Quality selectedQuality = videoEpisodeModel.getQualityDisplay().getValue();
        for (int i = 0; i < qualities.size(); i++) {
            if (selectedQuality == qualities.get(i).getQuality()) {
                initialIndex = i;
                break;
            }
        }

        RecyclerView.LayoutManager layoutManager = binding.content.getLayoutManager();
        adapter = new VideoQualityAdapter(getContext(), initialIndex);
        adapter.setOnSelectedListener(videoQualityWrap -> {
            videoEpisodeModel.getQuality().setValue(videoQualityWrap.getQuality());
            ItemVideoQualityBinding.bind(Objects.requireNonNull(layoutManager.getChildAt(videoQualityWrap.getPrePosition())))
                    .quality.setTextColor(Color.WHITE);
        });
        adapter.appendHead(qualities);

        ViewUtils.linkAdapter(binding.content, adapter);

        Window window = this.getWindow();
        if (window != null) {
            window.setGravity(Gravity.END);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(getContext().getResources().getDimensionPixelSize(R.dimen.dialog_width), WindowManager.LayoutParams.MATCH_PARENT);
            window.setDimAmount(0f);
        }

        setCancelable(true);
    }
}
