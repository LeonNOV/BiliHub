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
import com.leon.bilihub.beans.publicBeans.resources.video.VideoDetail;
import com.leon.bilihub.databinding.DialogVideoParameterBinding;
import com.leon.bilihub.model.LivePlayerModel;
import com.leon.bilihub.model.VideoPlayerModel;
import com.leon.bilihub.ui.adapters.video.VideoSubtitleAdapter;
import com.leon.bilihub.ui.adapters.video.live.LiveRoadAdapter;
import com.leon.bilihub.utils.ViewUtils;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class SubtitleDialog extends VideoPreferenceDialog {

    public SubtitleDialog(Context context) {
        super(context);
    }

    @Override
    protected void init() {
//        VideoPlayerModel videoPlayerModel = new ViewModelProvider(ViewUtils.scanForActivity(getContext())).get(VideoPlayerModel.class);
//        List<VideoDetail.Data.View.Subtitle.Data> subtitleList = videoPlayerModel.getSubtitle().getValue();
//
//        VideoSubtitleAdapter adapter = new VideoSubtitleAdapter(context, 0);
//
//        adapter.setOnSelectedListener(data -> {
//            videoPlayerModel.getSubtitle().setValue(data);
//
//            liveRoadList.get(roadWrap.getPosition()).setSelected(false);
//
//            adapter.notifyItemChanged(roadWrap.getPosition());
//        });
//        adapter.appendHead(subtitleList);
//
//
//        ViewUtils.linkAdapter(binding.content, adapter);
    }
}
