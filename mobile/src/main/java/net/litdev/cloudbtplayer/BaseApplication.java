package net.litdev.cloudbtplayer;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import net.litdev.cloudbtplayer.utils.ImageOptHelper;

import java.io.File;

/**
 * Created by litde on 2016/5/27.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initImageLoader(this);
    }

    /**
     * 图片处理
     *
     * @param context
     */
    private void initImageLoader(Context context) {
        //自定义缓存目录
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "imageloader/Cache");
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2).discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                //.diskCache(new UnlimitedDiskCache(cacheDir))//自定义缓存路径
                .defaultDisplayImageOptions(ImageOptHelper.getImgOptions()).build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
}
