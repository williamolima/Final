package com.exemples.lembretes

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class Banco(context: Context):SQLiteOpenHelper(context,"lembretes.db",null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table if not exists lembretes(" +
                "id integer primary key autoincrement," +
                "descricao text," +
                "data      text)" +
                
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table lembretes")
        this.onCreate(db)
    }
}
