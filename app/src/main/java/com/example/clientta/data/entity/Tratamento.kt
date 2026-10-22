package com.example.clientta.data.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Locale

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
    val ativo: Boolean = true,
) {
    /**
     * Retorna o preço do tratamento formatado em moeda brasileira (ex: R$ 120,00).
     */
    @get:Ignore
    val precoFormatado: String
        get() = String.format(Locale("pt", "BR"), "R$ %.2f", preco)

    /**
     * Retorna a duração estimada formatada de forma legível (ex: "50 min" ou "1h 15min").
     */
    @get:Ignore
    val duracaoFormatada: String
        get() = if (duracao >= 60) {
            val horas = duracao / 60
            val minutosRestantes = duracao % 60
            if (minutosRestantes > 0) "${horas}h ${minutosRestantes}min" else "${horas}h"
        } else {
            "$duracao min"
        }
}
