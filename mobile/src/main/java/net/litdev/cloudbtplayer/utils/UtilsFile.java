package net.litdev.cloudbtplayer.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by litde on 2016/5/30.
 */
public class UtilsFile {

    /**
     * 判断文件名是否是视频
     *
     * @param file_name
     * @return
     */
    public static boolean ExistsVideo(String file_name) {
        if (TextUtils.isEmpty(file_name)) {
            return false;
        }
        file_name = file_name.replace(" ", "");
        int last_point_index = file_name.lastIndexOf(".");
        if (last_point_index == -1) {
            return false;
        }
        String ext = file_name.substring(last_point_index + 1).toLowerCase();
        for (String item : Constants.VIDEO_EXT) {
            if(ext.equals(item)){
                return true;
            }
        }
        return false;
    }
}
