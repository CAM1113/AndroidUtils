package com.cam.androidutils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.IntRange;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Android工具类，提供公有的静态方法来处理系统开发的常用工具
 */
public class AndroidUtils {

    public static class BitmapUtils
    {

    }

    public static class SystemUtils
    {
        /**
         * 设置StatusBar中的字体颜色
         * Android系统提供的StatusBar字体颜色只有两种(黑+白)
         * @param activity
         * @param dark dark==false，字体颜色为白色 dark==true，字体颜色为黑色
         *调用此方法
         *因为一直会携带SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN这个flag，
         *那么默认界面会变成全屏模式
         *即StatusBar会在Activity的界面布局上面，
         *写布局文件时要为StatusBar的高度预留空间
         *如果不想使用全屏模式，需要在根布局中设置FitsSystemWindows属性为true
         */
        public static void setAndroidNativeLightStatusBar(Activity activity, boolean dark)
        {
            View decor = activity.getWindow().getDecorView();
            if (dark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }

        /**
         * 将StatusBar设置为全透明,API等级>=24
         *调用此方法
         *因为一直会携带SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN这个flag，
         *那么默认界面会变成全屏模式
         *即StatusBar会在Activity的界面布局上面，
         *写布局文件时要为StatusBar的高度预留空间
         *如果不想使用全屏模式，需要在根布局中设置FitsSystemWindows属性为true
         * @param activity
         */
        public static void setStatusBarTransparent(Activity activity)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                Window window = activity.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        }

        /**
         * 将StatusBar设置为半透明,API等级>=24
         * @param activity
         */
        public static void setStatusBarHalfTransparent(Activity activity)
        {
            //如果版本在4.4及其以上
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Window window = activity.getWindow();
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }





    }
}
