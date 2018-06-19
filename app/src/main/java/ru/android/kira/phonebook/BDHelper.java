package ru.android.kira.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class BDHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "DB";
    public static final String TABLE = "contacts";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String FAMILY = "family";
    public static final String PHONE = "phone";
    public static final String PHOTO = "photo";

    public BDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE
                + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME +" TEXT,"
                + FAMILY +" TEXT,"
                + PHONE +" TEXT,"
                + PHOTO +" TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  void addContact (Contact contact) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, contact.name);
        values.put(FAMILY, contact.family);
        values.put(PHONE, contact.phone);
        values.put(PHOTO, contact.photo);
        db.insert(TABLE, null, values);
        db.close();
    }

    public ArrayList<Contact> getAll(){
        ArrayList<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setFamily(cursor.getString(2));
                contact.setPhone(cursor.getString(3));
                contact.setPhoto(cursor.getString(4));
                contacts.add(contact);
            } while (cursor.moveToNext());
        }

        return contacts;
    }

}
