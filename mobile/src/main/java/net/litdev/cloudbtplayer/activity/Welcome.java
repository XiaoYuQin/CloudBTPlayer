package net.litdev.cloudbtplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import net.litdev.cloudbtplayer.R;

/**
 * Created by litde on 2016/5/26.
 */
public class Welcome extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(Welcome.this,MainActivity.class));
                finish();

            }
        }).start();

    }
}
