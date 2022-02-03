package com.example.lisadedesejo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class TelaCadastro : AppCompatActivity() {
    private lateinit var btCadastrar: Button
    private lateinit var btCancelar: Button
    private lateinit var etDescricao: TextView
    private lateinit var etData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        this.btCadastrar = findViewById(R.id.btCadastrar)
        this.btCancelar = findViewById(R.id.btCancelar)
        this.etDescricao = findViewById(R.id.etDescricao)
        this.etData.setOnClickListener({
            val dia = intent.getIntExtra("dia", 0)
            val mes = intent.getIntExtra("mes", 0)
            val ano = intent.getIntExtra("ano",0)

            etData = dia.toString()+"/"+mes.toString()+"/"+ano.toString()

            return etData
            })

        this.btCadastrar.setOnClickListener({ cadastrarLembrete(it) })
        this.btCancelar.setOnClickListener({ finish() })

    }
    private fun cadastrarLembrete(view: View) {
        val descricao = etDescricao.text.toString()
        val data = etData.text.toString()
        val lembrete = Lembrete(descricao, data)
        val intent = Intent()
        intent.putExtra("LEMBRETE", lembrete)
        setResult(RESULT_OK, intent)
        finish()
    }
}
