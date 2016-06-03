package net.litdev.cloudbtplayer.api;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import net.litdev.cloudbtplayer.inter.VolleyDBMoveResponseListener;
import net.litdev.cloudbtplayer.utils.Constants;

/**
 * 豆瓣热门电影
 */
public class APIDBMovie {
    public static Request send(VolleyDBMoveResponseListener listener){
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Constants.DB_MOVIE,null, listener,listener);
        return jsonObjectRequest;
    }
}
