package com.example.lembretes

import java.io.Serializable

class Lembrete: Serializable {
    var descricao: String
    var data: String

    constructor(descricao: String, data: String){
        this.descricao = descricao
        this.data = data
    }

    override fun toString(): String {
        return "${this.descricao} - ${this.data}"
    }


}
