package net.litdev.cloudbtplayer.inter;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.litdev.cloudbtplayer.bean.BeanHashList;
import net.litdev.cloudbtplayer.bean.BeanHashPlayer;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by litde on 2016/5/27.
 */
public abstract class VolleyHashFileResponesListener implements Response.Listener<String>,Response.ErrorListener {

    @Override
    public void onResponse(String s) {
        Log.d("Volley","请求返回："+s);
        //code:http://xfcd.ctfs.ftn.taoka123.com/ftn_handler/658e6033047dbc513eece3610306af8eebe422a34240936834cda31f33808c6990388373af7a24acc2e0c91ccf2b557ce406e1ed72a0b9adbc456d9fdcf208bf#cookie:0aa62564|
        BeanHashPlayer bean = new BeanHashPlayer();
        try {
            String [] list = s.split("#");
            bean.setPlayer_url(list[0].replace("code:",""));
            bean.setCookie(list[1].replace("cookie:","").replace("|",""));
        }catch (Exception e){
            e.printStackTrace();
        }
        onSuccess(bean);
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Log.d("Volley","请求错误："+volleyError.getMessage());
    }

    public abstract void onSuccess(BeanHashPlayer bean);

}
