package net.litdev.cloudbtplayer.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索记录本地数据库操作类
 */
public class DALSearchHostory {
    private final static String TABLE_NAME = "search_hostory";
    private DatabaseHelper dbHelper;

    public DALSearchHostory(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * 添加一条数据
     *
     * @param word
     * @return
     */
    public void add(String word) {
        if (exists(word)) {
            update(word);
        } else {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("word", word);
            cv.put("time", System.currentTimeMillis());
            db.insert(TABLE_NAME, null, cv);
            db.close();
        }
    }

    /**
     * 是否存在
     *
     * @param word
     * @return
     */
    public boolean exists(String word) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where word=? ", new String[]{word});
        boolean isOK = c.moveToNext();
        c.close();
        db.close();
        return isOK;
    }

    /**
     * 更新时间
     *
     * @param word
     */
    public void update(String word) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "update " + TABLE_NAME + " set time=? where word=?";
        db.execSQL(sql, new String[]{String.valueOf(System.currentTimeMillis()), word});
        db.close();
    }

    /**
     * 删除所有的搜索记录
     *
     * @return
     */
    public boolean removeALL() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        boolean isOK = db.delete(TABLE_NAME, "", null) > 0;
        db.close();
        return isOK;
    }

    /**
     * 获取所有的历史纪录
     *
     * @return
     */
    public List<String> getList(String search_key) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] vals = null;
        String sql = "select * from " + TABLE_NAME + " ";
        if (!TextUtils.isEmpty(search_key)) {
            sql += " where word like ? order by time desc";
            vals = new String[]{"%" + search_key + "%"};
        }else
        {
            sql += " order by time desc";
        }
        Cursor c = db.rawQuery(sql, vals);
        List<String> list = new ArrayList<>();
        while (c.moveToNext()) {
            list.add(c.getString(c.getColumnIndex("word")));
        }
        c.close();
        db.close();
        return list;
    }

}
