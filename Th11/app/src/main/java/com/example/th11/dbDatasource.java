package com.example.th11;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class dbDatasource {
    dbHelper helper;
    SQLiteDatabase database;
    Context context;
    private String[] allColumn = {dbHelper.TBL_SP_ID, dbHelper.TBL_SP_NAME, dbHelper.TBL_SP_PRICE};

    public dbDatasource(Context context){
        this.context = context;
        this.helper = new dbHelper(context);
    }
    public dbDatasource open() throws SQLException {
        database = helper.getReadableDatabase();
        return this;
    }

    public void close(){
        helper.close();
    }

    public List<Person> loadData(){
        List<Person> sp = new ArrayList<>();

        Cursor cursor = database.query(dbHelper.TBL_SP, allColumn, null, null,
                null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Person sanPham = cursorToSP(cursor);
            sp.add(sanPham);
            cursor.moveToNext();
        }
        cursor.close();
        return sp;
    }

    public void delete(Person sp){
        String selection = dbHelper.TBL_SP_ID + " = ?";
        String[] selectionArgs = new String[] {String.valueOf(sp.getId())};
        database.delete(dbHelper.TBL_SP, selection, selectionArgs);
    }

    private Person cursorToSP(Cursor cursor) {
        Person cursorSp = new Person(cursor.getString(0), cursor.getString(1), cursor.getInt(2));
        return cursorSp;
    }
}
