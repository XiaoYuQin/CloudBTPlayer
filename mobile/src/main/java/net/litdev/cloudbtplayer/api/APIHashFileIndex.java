package net.litdev.cloudbtplayer.api;

import android.app.Dialog;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import net.litdev.cloudbtplayer.inter.VolleyHashFileResponesListener;
import net.litdev.cloudbtplayer.utils.Constants;

/**
 * Created by litde on 2016/6/1.
 */
public class APIHashFileIndex {
    public static Request send(String hash, String index, VolleyHashFileResponesListener listener){
        String url;
        try {
            url = String.format(Constants.PARSE_VIDEO_URI_1,hash,index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        StringRequest stringRequest = new StringRequest(url,listener,listener);
        return stringRequest;
    }
}
