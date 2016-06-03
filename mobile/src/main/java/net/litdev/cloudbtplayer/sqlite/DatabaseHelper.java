package net.litdev.cloudbtplayer.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import net.litdev.cloudbtplayer.utils.Constants;

/**
 * Created by litde on 2016/5/31.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, Constants.DB_SEARCH_HOSTORY_NAME, null, Constants.DB_SEARCH_HOSTORY_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql  ="create table search_hostory(word varchar(50),time varchar(20));";
        db.execSQL(sql);
        //收藏
        String starsSql = "create table bt_stars(title varchar(50),date varchar(10),size varchar(10),hot varchar(5),hash varchar(50),time varchar(20));";
        db.execSQL(starsSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级
    }
}
