package com.example.lembretes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var btAdd: FloatingActionButton
    private lateinit var lvLembretes: ListView
    private lateinit var lembretes: ArrayList<Lembrete>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendar.setOnDateChangeListener(CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
            val intent = Intent(this, TelaCadastro::class.java)
            intent.putExtra("dia",dayOfMonth)
            intent.putExtra("mes",month)
            intent.putExtra("ano",year)
            startActivity(intent)
        })

        this.lembretes = ArrayList()
        this.btAdd = findViewById(R.id.fabAdicionar)
        this.lvLembretes = findViewById(R.id.lvLembretes)

        val layout = android.R.layout.simple_list_item_1
        this.lvLembretes.adapter = ArrayAdapter<Lembrete>(this, layout, this.lembretes)

        this.btAdd.setOnClickListener({ adicionarLembrete(it) })

        this.lvLembretes.setOnItemClickListener(ClickNoItemDaLista())
        this.lvLembretes.setOnItemLongClickListener(ClickLongoNoItemDaLista())
    }

    private fun adicionarLembrete(view: View){
        val intent = Intent(this, TelaCadastro::class.java)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val lembrete = data?.getSerializableExtra("LEMBRETE") as Lembrete
                (this.lvLembretes.adapter as ArrayAdapter<Lembrete>).add(lembrete)
            }
        }
    }

    inner class ClickNoItemDaLista: AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val lembrete = this@MainActivity.lembretes[position]
            Toast.makeText(this@MainActivity, "${desejo.descricao} - ${desejo.data}", Toast.LENGTH_SHORT).show()
        }
    }

    inner class ClickLongoNoItemDaLista: AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
            val lembretes = this@MainActivity.lembretess[position]
            (this@MainActivity.lvLembretes.adapter as ArrayAdapter<Lembrete>).remove(desejo)
            return true
        }
    }
}


