package com.linweiyuan.mhp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.linweiyuan.mhp.common.Constant
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, Constant.DB_NAME, null, 1) {
    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {}

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
}

val Context.database: DatabaseOpenHelper get() = DatabaseOpenHelper.getInstance(applicationContext)
