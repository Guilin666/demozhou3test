package bwie.example.com.demozhou3test.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Sqlutils {

    private SQLiteDatabase db;

    public Sqlutils(Context context) {
        SqlHelper helper = new SqlHelper(context);
        db = helper.getWritableDatabase();
    }



    public long insert(String data) {
        ContentValues values = new ContentValues();
        values.put("data", data);
        return db.insert("bwie", null, values);
    }


    //查询字符串
    public  String query() {
        String dd="";
        Cursor cursor = db.rawQuery("select data from bwie", null);
        while (cursor.moveToNext()) {
            String data = cursor.getString(cursor.getColumnIndex("data"));
            dd= data;
        }
        return dd;
    }

    //查询全部
    public   List<String> select() {
        List<String> mDatas= new ArrayList<>();
        Cursor cursor = db.rawQuery("select data from bwie", null);
        while (cursor.moveToNext()) {
            String data = cursor.getString(cursor.getColumnIndex("data"));
            mDatas.add(data);
        }
        return mDatas;
    }


    //删除全部
    public void clear() {
        db.delete("bwie",null,null);
    }
}

