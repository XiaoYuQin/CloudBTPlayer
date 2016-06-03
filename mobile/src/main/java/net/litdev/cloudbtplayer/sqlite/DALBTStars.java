package net.litdev.cloudbtplayer.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.litdev.cloudbtplayer.bean.BeanBTSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by litde on 2016/6/2.
 */
public class DALBTStars {
    private final static String TABLE_NAME = "bt_stars";
    private DatabaseHelper dbHelper;
    //收藏成功
    public static final int STARS_STATE_OK = 0;
    //已经收藏了
    public static final int STARS_STATE_EXISTS = 1;

    public DALBTStars(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * 添加收藏
     *
     * @param bean
     * @return
     */
    public int add(BeanBTSearch bean) {
        if(exists(bean.getHash())){
            return STARS_STATE_EXISTS;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", bean.getTitle());
        cv.put("date",bean.getDate());
        cv.put("hot",bean.getHot());
        cv.put("size",bean.getSize());
        cv.put("hash",bean.getHash());
        cv.put("time", System.currentTimeMillis());
        db.insert(TABLE_NAME, null, cv);
        db.close();
        return STARS_STATE_OK;
    }

    /**
     * 判断有没有收藏
     *
     * @param hash
     * @return
     */
    public boolean exists(String hash) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where hash=? ", new String[]{hash});
        boolean isOK = c.moveToNext();
        c.close();
        db.close();
        return isOK;
    }

    /**
     * 清空收藏
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
     * 删除一条记录
     * @param hash
     * @return
     */
    public boolean remove(String hash){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        boolean isOK = db.delete(TABLE_NAME,"hash=?",new String[]{hash}) > 0;
        db.close();
        return isOK;
    }

    /**
     * 获取所有的收藏
     *
     * @return
     */
    public List<BeanBTSearch> getList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] vals = null;
        String sql = "select * from " + TABLE_NAME + " order by time desc";
        Cursor c = db.rawQuery(sql,vals);
        List<BeanBTSearch> list = new ArrayList<>();
        while (c.moveToNext()) {
            BeanBTSearch bean = new BeanBTSearch();
            bean.setSize(c.getString(c.getColumnIndex("size")));
            bean.setHash(c.getString(c.getColumnIndex("hash")));
            bean.setDate(c.getString(c.getColumnIndex("date")));
            bean.setTitle(c.getString(c.getColumnIndex("title")));
            bean.setHot(c.getString(c.getColumnIndex("hot")));
            list.add(bean);
        }
        c.close();
        db.close();
        return list;
    }

}
