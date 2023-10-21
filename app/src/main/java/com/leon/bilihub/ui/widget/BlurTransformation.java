package com.leon.bilihub.ui.widget;

/**
 * @Author Leon
 * @Time 2023/10/21
 * @Desc
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import com.squareup.picasso.Transformation;

public class BlurTransformation implements Transformation {

    private RenderScript rs;
    private float radius;

    public BlurTransformation(Context context, float radius) {
        rs = RenderScript.create(context, RenderScript.ContextType.NORMAL, RenderScript.CREATE_FLAG_NONE);
        this.radius = radius;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap blurredBitmap = source.copy(Bitmap.Config.ARGB_8888, true);

        // 创建 RenderScript 对象和高斯模糊脚本
        Allocation input = Allocation.createFromBitmap(rs, source, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
        Allocation output = Allocation.createTyped(rs, input.getType());
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setInput(input);

        // 设置模糊半径
        script.setRadius(radius);

        // 执行高斯模糊操作
        script.forEach(output);

        // 将结果复制到目标位图
        output.copyTo(blurredBitmap);

        // 释放资源
        source.recycle();
        rs.destroy();

        return blurredBitmap;
    }

    @Override
    public String key() {
        return "blur";
    }
}
