package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PropertyDB";
    private static final int DATABASE_VERSION = 2; // <-- BERUBAH JADI 2 (Biar tabel direfresh)

    private static final String TABLE_HOUSES = "houses";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_PRICE = "price";
    private static final String KEY_DESC = "description";
    private static final String KEY_IMAGE = "image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HOUSES_TABLE = "CREATE TABLE " + TABLE_HOUSES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITLE + " TEXT,"
                + KEY_PRICE + " TEXT,"
                + KEY_DESC + " TEXT,"
                + KEY_IMAGE + " TEXT" + ")"; // <-- BERUBAH JADI TEXT
        db.execSQL(CREATE_HOUSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOUSES);
        onCreate(db);
    }

    public void addHouse(House house) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, house.getTitle());
        values.put(KEY_PRICE, house.getPrice());
        values.put(KEY_DESC, house.getDescription());
        values.put(KEY_IMAGE, house.getImageUri()); // <-- Simpan URI

        db.insert(TABLE_HOUSES, null, values);
        db.close();
    }

    public List<House> getAllHouses() {
        List<House> houseList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_HOUSES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                House house = new House(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4) // <-- Ambil URI
                );
                houseList.add(house);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return houseList;
    }

    public int updateHouse(House house) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, house.getTitle());
        values.put(KEY_PRICE, house.getPrice());
        values.put(KEY_DESC, house.getDescription());
        values.put(KEY_IMAGE, house.getImageUri()); // <-- Update URI

        return db.update(TABLE_HOUSES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(house.getId())});
    }

    public void deleteHouse(House house) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HOUSES, KEY_ID + " = ?",
                new String[]{String.valueOf(house.getId())});
        db.close();
    }
}