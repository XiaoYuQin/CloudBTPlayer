package net.litdev.cloudbtplayer.utils;

import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * 禁用文本框
 */
public class ForbiddenEditText implements View.OnTouchListener {
    EditText temp;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        temp= (EditText)v;
        temp.setInputType(InputType.TYPE_NULL);
        return false;
    }
}
