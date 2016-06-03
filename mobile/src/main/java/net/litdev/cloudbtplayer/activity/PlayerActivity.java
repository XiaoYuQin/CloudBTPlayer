package net.litdev.cloudbtplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import net.litdev.cloudbtplayer.BaseActivity;
import net.litdev.cloudbtplayer.R;

import java.util.HashMap;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by litde on 2016/6/1.
 */
public class PlayerActivity extends BaseActivity {
    private JCVideoPlayerStandard jcVideoPlayerStandard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.custom_videoplayer_standard);
        Intent intent = getIntent();
        String videoPath = intent.getStringExtra("path");
        String cookie = intent.getStringExtra("cookie");
        Log.i("APP",videoPath);
        Log.i("APP",cookie);
        Map<String,String> head = new HashMap<>();
        head.put("Cookie","FTN5K="+cookie);//"FTN5K=5f078157"
        jcVideoPlayerStandard.setUp(videoPath,head,"标题");
        jcVideoPlayerStandard.ivStart.performClick();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jcVideoPlayerStandard.releaseAllVideos();
    }
}
