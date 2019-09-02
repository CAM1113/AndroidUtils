package com.cam.androidutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.IntRange;

public class BitmapUtils {
    //获取毛玻璃效果的Bitmap
    //高斯模糊
    /**
     *
     * @param context
     * @param source 源Bitmap
     * @param radius 模糊半径（范围1~25，因此无法得到非常模糊的图） 半径越大越模糊
     * @param scale  压缩比例，进行高斯前对图片进行缩放，可以提高运行效率
     * @return 高斯模糊处理后的Bitmap
     */
    public static Bitmap rsBlur(Context context,
                                Bitmap source,
                                int radius,
                                float scale)
    {
        //图片压缩处理
        int width = Math.round(source.getWidth() * scale);
        int height = Math.round(source.getHeight() * scale);
        Bitmap inputBmp = Bitmap.createScaledBitmap(source, width, height, false);

        RenderScript renderScript = RenderScript.create(context);
        // Allocate memory for Renderscript to work with
        final Allocation input = Allocation.createFromBitmap(renderScript, inputBmp);
        final Allocation output = Allocation.createTyped(renderScript, input.getType());
        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setInput(input);

        // Set the blur radius
        scriptIntrinsicBlur.setRadius(radius);

        // Start the ScriptIntrinisicBlur
        scriptIntrinsicBlur.forEach(output);

        // Copy the output to the blurred bitmap
        output.copyTo(inputBmp);

        renderScript.destroy();
        return inputBmp;
    }
}
