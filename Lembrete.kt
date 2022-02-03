package com.example.lembretes

import java.io.Serializable

class Lembrete: Serializable {
    var descricao: String
    var data: String

    constructor(descricao: String, data: String){
        this.descricao = descricao
        this.data = data
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        val dia = intent.getIntExtra("dia", 0)
        val mes = intent.getIntExtra("mes", 0)
        val ano = intent.getIntExtra("ano",0)

        tvData.text = dia.toString()+"/"+mes.toString()+"/"+ano.toString()

        return tvData.text

    }

    override fun toString(): String {
        return "${this.descricao} - ${this.data}"
    }


}
