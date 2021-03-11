package com.vend.test.config.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
Created By Syed Ovais Akhtar On 3/11/21 1:26 PM2
 **/
class PostDB(context: Context) : SQLiteOpenHelper(context,"post_db",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(getPostTableQuery())
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }

    private fun getPostTableQuery() : String{

        return "CREATE TABLE posts(post_id INTEGER, user_id  INTEGER , post_title TEXT , post_body TEXT)"
    }
}