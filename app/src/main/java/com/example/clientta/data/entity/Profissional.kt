package com.example.clientta.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidade que representa um profissional cadastrado na clínica.
 */
@Entity(tableName = "profissionais")
data class Profissional(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val email: String,
    val telefone: String,
    val especialidade: String,
    val senha: String
)
