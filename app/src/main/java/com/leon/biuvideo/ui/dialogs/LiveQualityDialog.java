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
import com.leon.biuvideo.beans.publicBeans.resources.live.LiveStream;
import com.leon.biuvideo.databinding.DialogVideoParameterBinding;
import com.leon.biuvideo.model.LivePlayerModel;
import com.leon.biuvideo.ui.adapters.video.live.LiveQualityAdapter;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class LiveQualityDialog extends AppCompatDialog {
    private LivePlayerModel livePlayerModel;
    private DialogVideoParameterBinding binding;

    public LiveQualityDialog(@NonNull Context context) {
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
        livePlayerModel = new ViewModelProvider(ViewUtils.scanForActivity(getContext())).get(LivePlayerModel.class);
        List<LiveStream.Data.QualityDescription> liveQualityList = livePlayerModel.getLiveQualityList().getValue();

        LiveQualityAdapter adapter = new LiveQualityAdapter(getContext());
        adapter.setOnSelectedListener(roadWrap -> {
            livePlayerModel.getLiveQuality().setValue(roadWrap.getQuality());

            liveQualityList.get(roadWrap.getPosition()).setSelected(false);

            adapter.notifyItemChanged(roadWrap.getPosition());
        });
        adapter.appendHead(liveQualityList);


        ViewUtils.linkAdapter(binding.content, adapter);
    }
}
