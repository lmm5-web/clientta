package com.example.clientta.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidade que representa um cliente cadastrado no aplicativo Clientta.
 */
@Entity(tableName = "clientes")
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val cpf: String,
    val dataNascimento: String,
    val telefone: String,
    val email: String,
    val senha: String,
    val endereco: String? = null,
    val observacoes: String? = null
)
