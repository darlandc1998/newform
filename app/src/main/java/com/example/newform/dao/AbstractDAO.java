package com.example.newform.dao;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.newform.database.DBOpenHelper;
import com.example.newform.utils.UtilDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class AbstractDAO {

    private static final DateFormat dateFormat = UtilDate.getDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    protected SQLiteDatabase db;
    protected DBOpenHelper db_helper;

    protected final void Open() {
        db = db_helper.getWritableDatabase();
    }

    protected final void Close() {
        db_helper.close();
    }

    protected String getCursorString(Cursor cursor, String column) {
        int columnIndex = cursor.getColumnIndex(column);
        if (!cursor.isNull(columnIndex)) { return cursor.getString(columnIndex); } else { return null; }
    }

    protected Double getCursorDouble(Cursor cursor, String column) {
        int columnIndex = cursor.getColumnIndex(column);
        if (!cursor.isNull(columnIndex)) { return cursor.getDouble(columnIndex); } else { return null; }
    }

    protected Long getCursorLong(Cursor cursor, String column) {
        int columnIndex = cursor.getColumnIndex(column);
        if (!cursor.isNull(columnIndex)) { return cursor.getLong(columnIndex); } else { return null; }
    }

    protected Integer getCursorInteger(Cursor cursor, String column) {
        int columnIndex = cursor.getColumnIndex(column);
        if (!cursor.isNull(columnIndex)) { return (int) cursor.getLong(columnIndex); } else { return null; }
    }

    protected Date getCursorDate(Cursor cursor, String column) {
        int columnIndex = cursor.getColumnIndex(column);
        if (!cursor.isNull(columnIndex)) {
            try {
                return dateFormat.parse(cursor.getString(columnIndex));
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else { return null; }
    }

    public static String getDateAnsiFormat(Date date) {

        if (date == null) return null;

        return dateFormat.format(date);
    }


}
