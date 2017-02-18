package com.example.birthdaytime;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;

import com.example.birthdaytime.getterSetter.birthdayInfo;

import java.util.ArrayList;

/**
 * Created by admin on 1/9/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name = "birthday";
    static int version = 1;
    String createTableSql = "CREATE TABLE if not exists \"addBirthday\" (\n" +
            "\t`Name`\tTEXT,\n" +
            "\t`birthYear`\tINTEGER,\n" +
            "\t`birthMonth`\tINTEGER,\n" +
            "\t`birthDay`\tINTEGER,\n" +
            "\t`reminderHour`\tINTEGER,\n" +
            "\t`reminderMin`\tINTEGER,\n" +
            "\t`phoneNumber`\tNUMERIC,\n" +
            "\t`massage`\tINTEGER DEFAULT 'Happy Birthday!!!!! Have a blasting Birthday',\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t`flag`\tINTEGER DEFAULT 0\n" +
            ")";

    public DatabaseHelper(FragmentActivity context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTableSql);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertBirthday(ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("addBirthday", "", cv);

    }

    public ArrayList<birthdayInfo> getBirthdayList() {
        String sql = "select * from addBirthday";
        Cursor c = getWritableDatabase().rawQuery(sql, null);
        ArrayList<birthdayInfo> list = new ArrayList<birthdayInfo>();
        while (c.moveToNext()) {
            birthdayInfo info = new birthdayInfo();
            info.setName(c.getString(c.getColumnIndex("Name")));
            info.setBirthYear(c.getString(c.getColumnIndex("birthYear")));
            info.setBirthMonth(c.getString(c.getColumnIndex("birthMonth")));
            info.setBirthDay(c.getString(c.getColumnIndex("birthDay")));
            info.setAlramHour(c.getString(c.getColumnIndex("reminderHour")));
            info.setAlramMinute(c.getString(c.getColumnIndex("reminderMin")));
            info.setPhoneNumber(c.getString(c.getColumnIndex("phoneNumber")));
            info.setMassage(c.getString(c.getColumnIndex("massage")));
            info.setId(c.getString(c.getColumnIndex("id")));
            list.add(info);

        }
        c.close();
        return list;
    }

    public birthdayInfo getBirthdayInfo(String id) {
        String sql = "select * from addBirthday where id=" + id;
        Cursor c = getWritableDatabase().rawQuery(sql, null);
        ArrayList<birthdayInfo> list = new ArrayList<birthdayInfo>();
        birthdayInfo info = new birthdayInfo();
        while (c.moveToNext()) {
            info.setName(c.getString(c.getColumnIndex("Name")));
            info.setBirthYear(c.getString(c.getColumnIndex("birthYear")));
            info.setBirthMonth(c.getString(c.getColumnIndex("birthMonth")));
            info.setBirthDay(c.getString(c.getColumnIndex("birthDay")));
            info.setAlramHour(c.getString(c.getColumnIndex("reminderHour")));
            info.setAlramMinute(c.getString(c.getColumnIndex("reminderMin")));
            info.setPhoneNumber(c.getString(c.getColumnIndex("phoneNumber")));
            info.setMassage(c.getString(c.getColumnIndex("massage")));


        }
        c.close();
        return info;
    }


    public void updateBirthday(ContentValues cv, String id) {
        getWritableDatabase().update("addBirthday", cv, "id=?", new String[]{id});

    }

    public void delectBirthday(String id) {
        getWritableDatabase().delete("addBirthday", "id=?", new String[]{id});
    }
}
