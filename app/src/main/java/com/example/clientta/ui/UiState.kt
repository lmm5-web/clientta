// Fronteira: Define os quatro estados possíveis da tela (Carregando, Vazio, Erro, Sucesso) usando sealed interface.
// Motivo: Garante exaustividade no when das telas e tratamento de todas as situações de UI.
// Se sumir: O aplicativo perde o controle de estados e pode exibir telas brancas ou dados inconsistentes.

package com.example.clientta.ui

import com.example.clientta.data.entity.Tratamento

sealed interface UiState {
    data object Carregando : UiState
    data object Vazio : UiState
    data class Erro(val mensagem: String) : UiState
    data class Sucesso(val itens: List<Tratamento>) : UiState
}
