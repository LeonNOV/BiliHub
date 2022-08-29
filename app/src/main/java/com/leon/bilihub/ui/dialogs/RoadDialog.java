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
import com.leon.bilihub.beans.publicBeans.resources.live.LiveStream;
import com.leon.bilihub.databinding.DialogVideoParameterBinding;
import com.leon.bilihub.model.LivePlayerModel;
import com.leon.bilihub.ui.adapters.video.live.LiveRoadAdapter;
import com.leon.bilihub.utils.ViewUtils;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class RoadDialog extends AppCompatDialog {
    private LivePlayerModel livePlayerModel;
    private DialogVideoParameterBinding binding;

    public RoadDialog(@NonNull Context context) {
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
        List<LiveStream.Data.Durl> liveRoadList = livePlayerModel.getLiveRoadList().getValue();

        LiveRoadAdapter adapter = new LiveRoadAdapter(getContext());
        adapter.setOnSelectedListener(roadWrap -> {
            livePlayerModel.getLiveRoad().setValue(roadWrap.getRoad());

            liveRoadList.get(roadWrap.getPosition()).setSelected(false);

            adapter.notifyItemChanged(roadWrap.getPosition());
        });
        adapter.appendHead(liveRoadList);


        ViewUtils.linkAdapter(binding.content, adapter);
    }
}
