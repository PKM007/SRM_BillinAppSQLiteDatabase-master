package com.comlu.navinsandroidtutorial.srm_billinappsqlitedatabase;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_SRMPRICELIST = "pricelisttable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ITEMPRICE1 = "itemprice1";
    public static final String COLUMN_ITEMPRICE2 = "itemprice2";

    private static final String DATABASE_NAME = "srmpricelist.db";
    private static final int DATABASE_VERSION = 1;
    private String[] allColumns = { COLUMN_ID,
           COLUMN_ITEMPRICE1,COLUMN_ITEMPRICE2 };

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_SRMPRICELIST + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_ITEMPRICE1
            + " integer not null,"+COLUMN_ITEMPRICE2+" integer not null)";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);


        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEMPRICE1, 10); // Contact Name
        values.put(COLUMN_ITEMPRICE2, 10); // Contact Phone Number

        // Inserting Row
        database.insert(TABLE_SRMPRICELIST, null, values);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_SRMPRICELIST);
        onCreate(db);
    }

    // Updating single contact
    public int updatePrice(ItemPrice itemPrice) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEMPRICE1, itemPrice.getItemPrice1());
        values.put(COLUMN_ITEMPRICE2, itemPrice.getItemPrice2());

        // updating row
        return db.update(TABLE_SRMPRICELIST, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(1) });
    }
    // Getting contacts Count
    public ItemPrice getItemPrice() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(MySQLiteHelper.TABLE_SRMPRICELIST,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + 1, null,
                null, null, null);
        cursor.moveToFirst();
        ItemPrice newitemPrice = cursorToItemPrice(cursor);
        cursor.close();
        db.close();
        // return count
        return newitemPrice;
    }
    ItemPrice cursorToItemPrice(Cursor cursor)
    {
        ItemPrice itemPrice = new ItemPrice();
        itemPrice.setId(cursor.getLong(0));
        itemPrice.setItemPrice1(cursor.getInt(1));
        itemPrice.setItemPrice2(cursor.getInt(2));

        return itemPrice;
    }


}