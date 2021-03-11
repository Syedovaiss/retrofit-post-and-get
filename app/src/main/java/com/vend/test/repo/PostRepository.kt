package com.vend.test.repo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.vend.test.config.db.PostDB
import com.vend.test.model.PostItem

/**
Created By Syed Ovais Akhtar On 3/11/21 1:31 PM2
 **/
class PostRepository(context: Context) {

    private val db = PostDB(context)


    fun insertPosts(post: List<PostItem>) {
        for (items in post) {
            val contentValues = ContentValues()
            contentValues.put("post_id", items.id)
            contentValues.put("user_id", items.userId)
            contentValues.put("post_title", items.title)
            contentValues.put("post_body", items.body)
            db.writableDatabase.insertWithOnConflict("posts", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE)
        }

    }

    fun getAllPosts(): ArrayList<PostItem> {
        return arrayListOf()
    }
}