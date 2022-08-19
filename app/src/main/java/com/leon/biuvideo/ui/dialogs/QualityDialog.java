package com.leon.biuvideo.ui.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;

import com.leon.biuvideo.R;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoQuality;
import com.leon.biuvideo.databinding.DialogVideoParameterBinding;
import com.leon.biuvideo.ui.adapters.video.VideoQualityAdapter;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class QualityDialog extends AppCompatDialog {
    /**
     * bvid/seasonId or whatever
     */
    private List<VideoQuality> qualityList;

    public QualityDialog(@NonNull Context context) {
        super(context);
    }

    public QualityDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public QualityDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DialogVideoParameterBinding binding = DialogVideoParameterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        VideoQualityAdapter adapter = new VideoQualityAdapter(getContext());
        adapter.appendHead(qualityList);

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

    public void setQualityList(List<VideoQuality> qualityList) {
        this.qualityList = qualityList;
    }
}
