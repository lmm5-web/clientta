package com.example.clientta.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidade que representa um tratamento/serviço oferecido pela clínica.
 */
@Entity(tableName = "tratamentos")
data class Tratamento(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val descricao: String,
    val preco: Double,
    val duracao: Int, // Duração em minutos
    val ativo: Boolean = true
)
