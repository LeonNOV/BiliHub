package com.leon.biuvideo.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.target.ImageViewTarget;

/**
 * @Author Leon
 * @Time 2022/07/29
 * @Desc todo
 */
public class PictureModule extends ImageViewTarget<Bitmap> {

    public PictureModule(ImageView view) {
        super(view);
    }

    @Override
    protected void setResource(@Nullable Bitmap resource) {

    }
}
