package com.example.th11;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class dbHelper extends SQLiteOpenHelper {
    List<String> create_sql_list = new ArrayList<String>();
    String sql;

    public static final String DATABASE_NAME = "table.db";
    public static final int DATABASE_VERSION = 1;

    /**
     * Table
     */
    public static final String TBL_SP = "tblSanpham";
    public static final String TBL_SP_ID = "id";
    public static final String TBL_SP_NAME = "name";
    public static final String TBL_SP_PRICE = "price";


    public dbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void create_table(){
        sql = "CREATE TABLE " + TBL_SP + " (" +
                TBL_SP_ID + " TEXT, " +
                TBL_SP_NAME + " TEXT, " +
                TBL_SP_PRICE + " INTEGER);";
        create_sql_list.add(sql);

        sql = "INSERT INTO " + TBL_SP + " VALUES('NV_1','nguyễn đại nhân', 35)";
        create_sql_list.add(sql);
        sql = "INSERT INTO " + TBL_SP + " VALUES('NV_2','trần đại nghĩa', 35)";
        create_sql_list.add(sql);
        sql = "INSERT INTO " + TBL_SP + " VALUES('NV_3','hoàng đại lễ', 35)";
        create_sql_list.add(sql);
        sql = "INSERT INTO " + TBL_SP + " VALUES('NV_4','phạm đại trí', 35)";
        create_sql_list.add(sql);
        sql = "INSERT INTO " + TBL_SP + " VALUES('NV_5','trương đại tín', 35)";
        create_sql_list.add(sql);
        sql = "INSERT INTO " + TBL_SP + " VALUES('NV_6','hồ đại đức', 35)";
        create_sql_list.add(sql);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        create_table();
        for(int i = 0;i < create_sql_list.size();i++)
            db.execSQL(create_sql_list.get(i));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TBL_SP);
        onCreate(db);
    }
}
