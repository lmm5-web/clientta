package com.example.clientta.data.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Entidade que representa um cliente cadastrado no aplicativo Clientta.
 *
 * Armazena informações pessoais e de contato para agendamentos e pré-atendimento.
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
    val observacoes: String? = null,
) {
    /**
     * Retorna o CPF parcialmente mascarado para proteção de dados na UI (ex: "123.***.***-45"),
     * atendendo ao requisito de privacidade do cliente (RF10 do PRD).
     */
    @get:Ignore
    val cpfMascarado: String
        get() {
            val digitos = cpf.replace("\\D".toRegex(), "")
            return if (digitos.length == 11) {
                "${digitos.substring(0, 3)}.***.***-${digitos.substring(9)}"
            } else {
                cpf
            }
        }

    /**
     * Retorna o primeiro nome do cliente para saudações personalizadas na UI (ex: "Olá, Maria!").
     */
    @get:Ignore
    val primeiroNome: String
        get() = nome.trim().split("\\s+".toRegex()).firstOrNull() ?: nome

    /**
     * Retorna o telefone formatado no padrão (XX) XXXXX-XXXX se contiver 10 ou 11 dígitos.
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

    /**
     * Retorna as iniciais do nome do cliente para exibição em avatares de perfil (ex: "Maria Silva" -> "MS").
     */
    @get:Ignore
    val iniciais: String
        get() {
            val partes = nome.trim().split("\\s+".toRegex()).filter { it.isNotEmpty() }
            return when {
                partes.size >= 2 -> "${partes.first().first()}${partes.last().first()}".uppercase()
                partes.isNotEmpty() -> partes.first().first().toString().uppercase()
                else -> "C"
            }
        }
}
