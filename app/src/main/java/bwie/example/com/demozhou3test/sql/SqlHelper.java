package bwie.example.com.demozhou3test.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHelper extends SQLiteOpenHelper{


    public SqlHelper(Context context) {
        super(context,"info.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table bwie(id Integer primary key autoincrement ,data text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}