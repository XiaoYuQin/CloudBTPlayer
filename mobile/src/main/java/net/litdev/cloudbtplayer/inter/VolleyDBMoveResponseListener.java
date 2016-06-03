package net.litdev.cloudbtplayer.inter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.litdev.cloudbtplayer.bean.BeanDBMovie;

import org.json.JSONObject;

/**
 * Created by litde on 2016/5/30.
 */
public abstract class VolleyDBMoveResponseListener implements Response.Listener<JSONObject>,Response.ErrorListener {

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Log.d("Volley","请求错误："+volleyError.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        BeanDBMovie beanDBMovie = JSON.parseObject(response.toString(), BeanDBMovie.class);
        onSuccess(beanDBMovie);
    }

    public abstract void onSuccess(BeanDBMovie beanDBMovie);

}
