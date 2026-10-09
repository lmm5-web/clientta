package com.example.clientta.ui.navigation

sealed class NavTarget(val route: String) {
    data object Login : NavTarget("login")
    data object Cadastro : NavTarget("cadastro")
    data object Inicio : NavTarget("inicio")
    data object Tratamentos : NavTarget("tratamentos")
    data object DetalheTratamento : NavTarget("detalhe_tratamento/{tratamentoId}") {
        fun createRoute(tratamentoId: Long) = "detalhe_tratamento/$tratamentoId"
    }
    data object Agendar : NavTarget("agendar/{tratamentoId}") {
        fun createRoute(tratamentoId: Long) = "agendar/$tratamentoId"
    }
    data object Agendamentos : NavTarget("agendamentos")
    data object DetalheAgendamento : NavTarget("detalhe_agendamento/{agendamentoId}") {
        fun createRoute(agendamentoId: Long) = "detalhe_agendamento/$agendamentoId"
    }
    data object PreAtendimento : NavTarget("pre_atendimento/{agendamentoId}") {
        fun createRoute(agendamentoId: Long) = "pre_atendimento/$agendamentoId"
    }
    data object Perfil : NavTarget("perfil")
    data object AreaProfissional : NavTarget("area_profissional")
}
