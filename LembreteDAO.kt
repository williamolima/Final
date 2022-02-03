package com.exemple.lembretes

import android.content.ContentValues
import android.content.Context
import java.util.ArrayList

class TarefaDAO {
    var banco: DB

    constructor(context: Context) {
        this.banco = DB(context)
    }

    fun inserir(lembrete: Lembrete) {
        val cv = ContentValues()
        cv.put("descricao", lembrete.descricao)
        cv.put("data", lembrete.data)
        this.banco.writableDatabase.insert("lembrete", null, cv)
    }

    fun count(): Int {
        val colunas = arrayOf("id")
        val c = this.banco.readableDatabase.query("lembrete", colunas, null, null, null, null, null)
        return c.count
    }

    fun get(): ArrayList<Lembrete> {
        val lista = ArrayList<Lembrete>()
        val colunas = arrayOf("id", "descricao", "data")
        val c = this.banco.readableDatabase.query("lembretes", colunas, null, null, null, null, null)
        if (c.count > 0) {
            c.moveToFirst()
            do {
                val id = c.getInt(c.getColumnIndex("id"))
                val descricao = c.getString(c.getColumnIndex("descricao"))
                val data = c.getInt(c.getColumnIndex("data"))
                lista.add(Tarefa(id, nome, prioridade))
            } while (c.moveToNext())
        }
        c.close()
        return lista
    }

    fun get(id: Int): Lembrete? {

        val colunas = arrayOf("id", "descricao", "data")
        val c = this.banco.readableDatabase.query("lembretes", colunas, null, null, null, null, null)

        if (c.count > 0) {
            c.moveToFirst()
            do {
                if (c.getInt(c.getColumnIndex("id")) == id) {
                    val descricao = c.getString(c.getColumnIndex("descricao"))
                    val data = c.getInt(c.getColumnIndex("data"))
                    return Tarefa(id, descricao, data)
                }
            } while (c.moveToNext())
        }
        c.close()
        return null
    }

    fun delete(id: Int) {
        val where = "id = ?"
        val wherep = arrayOf(id.toString())
        this.banco.writableDatabase.delete("lembretes", where, wherep)
    }

    fun update(lembrete: Lembrete) {
        val cv = ContentValues()
        val where = "id = ?"
        val wherep = arrayOf(lembrete.id.toString())
        cv.put("descricao", lembrete.descricao)
        cv.put("data", lembrete.data)
        this.banco.writableDatabase.update("lembretes", cv, where, wherep)
    }
}
