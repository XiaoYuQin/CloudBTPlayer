package net.litdev.cloudbtplayer.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by litde on 2016/5/27.
 */
public class UtilsToast {
    private static Toast toast;

    /**
     * 显示
     * @param context
     * @param text
     */
    public static void show(Context context,String text){
        if(toast == null){
            toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        }
        else{
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

}
