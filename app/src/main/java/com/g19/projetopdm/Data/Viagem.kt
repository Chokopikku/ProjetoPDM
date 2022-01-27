package com.g19.projetopdm.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Viagem")
data class Viagem(
    @PrimaryKey(autoGenerate = true)
    val viagem_id: Int,
    val veiculo : String,
    val user : Int,
    val custo : Float,
    val data_inicio: String,
    val data_fim : String,
    val coordenadas_recolha : String,
    val coordenadas_entrega : String
)