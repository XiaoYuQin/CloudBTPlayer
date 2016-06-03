package net.litdev.cloudbtplayer.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import net.litdev.cloudbtplayer.R;

/**
 * ImageLoad配置文件
 */
public class ImageOptHelper {

    /**
     * 默认效果
     * @return
     */
    public static DisplayImageOptions getImgOptions() {
        DisplayImageOptions imgOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .cacheInMemory()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showStubImage(R.mipmap.timeline_image_loading)
                .showImageForEmptyUri(R.mipmap.timeline_image_loading)
                .showImageOnFail(R.mipmap.timeline_image_failure)
                .build();
        return imgOptions;
    }

    /**
     * 头像效果，加上圆角
     * @return
     */
    public static DisplayImageOptions getAvatarOptions() {
        DisplayImageOptions	avatarOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .cacheInMemory()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showStubImage(R.mipmap.avatar_default)
                .showImageForEmptyUri(R.mipmap.avatar_default)
                .showImageOnFail(R.mipmap.avatar_default)
                .displayer(new RoundedBitmapDisplayer(999))
                .build();
        return avatarOptions;
    }

    /**
     * 指定图片的圆角大小
     * @param cornerRadiusPixels
     * @return
     */
    public static DisplayImageOptions getCornerOptions(int cornerRadiusPixels) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .cacheInMemory()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showStubImage(R.mipmap.timeline_image_loading)
                .showImageForEmptyUri(R.mipmap.timeline_image_loading)
                .showImageOnFail(R.mipmap.timeline_image_loading)
                .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels)).build();
        return options;
    }

}