package com.example.clientta.data.entity

import androidx.room.Entity
import androidx.room.Ignore
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
    val senha: String,
    val nomeClinica: String? = null,
    val endereco: String? = null,
    val descricao: String? = null,
    val registroProfissional: String? = null,
    val ativo: Boolean = true,
) {
    /**
     * Retorna o primeiro nome do profissional para saudações personalizadas na UI (ex: "Ana").
     */
    @get:Ignore
    val primeiroNome: String
        get() = nome.trim().split("\\s+".toRegex()).firstOrNull() ?: nome

    /**
     * Retorna o telefone formatado no padrão (XX) XXXXX-XXXX se contiver apenas dígitos de 10 ou 11 caracteres.
     */
    @get:Ignore
    val telefoneFormatado: String
        get() {
            val apenasNumeros = telefone.replace("\\D".toRegex(), "")
            return when (apenasNumeros.length) {
                11 -> "(${apenasNumeros.substring(0, 2)}) ${apenasNumeros.substring(2, 7)}-${apenasNumeros.substring(7)}"
                10 -> "(${apenasNumeros.substring(0, 2)}) ${apenasNumeros.substring(2, 6)}-${apenasNumeros.substring(6)}"
                else -> telefone
            }
        }
}
