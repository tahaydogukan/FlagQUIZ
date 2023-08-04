package com.tahayasindogukan.flagquiz

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VeritabaniYardimcisi(context:Context)
    : SQLiteOpenHelper(context,"Bayrakquiz.sqlite",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var createtable= "CREATE TABLE \"bayraklar\" (\n" +
                "\t\"bayrak_id\"\tINTEGER,\n" +
                "\t\"bayrak_ad\"\tTEXT,\n" +
                "\t\"bayrak_resim\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"bayrak_id\" AUTOINCREMENT)\n" +
                ")"
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS bayraklar")
        onCreate(db)
    }
}

