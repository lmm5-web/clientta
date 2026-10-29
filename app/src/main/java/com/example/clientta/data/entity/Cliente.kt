package com.example.clientta.data.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Calendar

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
    val cep: String? = null,
    val logradouro: String? = null,
    val bairro: String? = null,
    val cidade: String? = null,
    val estado: String? = null,
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
     * Retorna o CPF completo formatado no padrão 000.000.000-00 se contiver 11 dígitos.
     */
    @get:Ignore
    val cpfFormatado: String
        get() {
            val digitos = cpf.replace("\\D".toRegex(), "")
            return if (digitos.length == 11) {
                "${digitos.substring(0, 3)}.${digitos.substring(3, 6)}.${digitos.substring(6, 9)}-${digitos.substring(9)}"
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

    /**
     * Retorna a idade do cliente calculada a partir da data de nascimento em anos.
     * Suporta formatos DD/MM/AAAA e AAAA-MM-DD.
     */
    @get:Ignore
    val idade: Int?
        get() {
            return try {
                val limpo = dataNascimento.trim()
                val partes = if (limpo.contains("/")) limpo.split("/") else limpo.split("-")
                if (partes.size == 3) {
                    val (dia, mes, ano) = if (partes[0].length == 4) {
                        Triple(partes[2].toInt(), partes[1].toInt(), partes[0].toInt())
                    } else {
                        Triple(partes[0].toInt(), partes[1].toInt(), partes[2].toInt())
                    }
                    val hoje = Calendar.getInstance()
                    var idadeCalculada = hoje[Calendar.YEAR] - ano
                    val mesAtual = hoje[Calendar.MONTH] + 1
                    val diaAtual = hoje[Calendar.DAY_OF_MONTH]
                    if (mesAtual < mes || (mesAtual == mes && diaAtual < dia)) {
                        idadeCalculada--
                    }
                    if (idadeCalculada in 0..120) idadeCalculada else null
                } else null
            } catch (_: Exception) {
                null
            }
        }

    /**
     * Retorna a idade do cliente formatada para exibição em fichas e perfil (ex: "28 anos").
     */
    @get:Ignore
    val idadeFormatada: String
        get() = idade?.let { "$it anos" } ?: "Idade não informada"

    /**
     * Retorna o CEP formatado no padrão 00000-000 se contiver 8 dígitos.
     */
    @get:Ignore
    val cepFormatado: String?
        get() {
            val digitos = cep?.replace("\\D".toRegex(), "") ?: return null
            return if (digitos.length == 8) {
                "${digitos.substring(0, 5)}-${digitos.substring(5)}"
            } else {
                cep
            }
        }

    /**
     * Retorna o endereço completo estruturado montado a partir dos campos individuais ou do campo endereco.
     */
    @get:Ignore
    val enderecoCompleto: String
        get() {
            val partes = listOfNotNull(
                logradouro.takeIf { !it.isNullOrBlank() },
                bairro.takeIf { !it.isNullOrBlank() },
                cidade?.let { c -> estado?.let { e -> "$c - $e" } ?: c } ?: estado
            )
            return when {
                partes.isNotEmpty() -> partes.joinToString(", ")
                !endereco.isNullOrBlank() -> endereco
                else -> "Endereço não informado"
            }
        }

    /**
     * Indica se o perfil do cliente possui informações completas (endereço/CEP e observações).
     */
    @get:Ignore
    val perfilCompleto: Boolean
        get() = (!endereco.isNullOrBlank() || !cep.isNullOrBlank()) && !observacoes.isNullOrBlank()
}
