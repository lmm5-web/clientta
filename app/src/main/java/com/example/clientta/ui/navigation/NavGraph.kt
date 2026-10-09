package com.example.clientta.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.clientta.ui.screens.agendamentos.AgendamentosListScreen
import com.example.clientta.ui.screens.agendamentos.AgendarScreen
import com.example.clientta.ui.screens.cadastro.CadastroScreen
import com.example.clientta.ui.screens.inicio.InicioScreen
import com.example.clientta.ui.screens.login.LoginScreen
import com.example.clientta.ui.screens.perfil.PerfilScreen
import com.example.clientta.ui.screens.preatendimento.PreAtendimentoScreen
import com.example.clientta.ui.screens.profissional.AreaProfissionalScreen
import com.example.clientta.ui.screens.tratamentos.DetalheTratamentoScreen
import com.example.clientta.ui.screens.tratamentos.TratamentosScreen
import com.example.clientta.viewmodel.AgendamentoViewModel
import com.example.clientta.viewmodel.CadastroViewModel
import com.example.clientta.viewmodel.LoginViewModel
import com.example.clientta.viewmodel.PerfilViewModel
import com.example.clientta.viewmodel.PreAtendimentoViewModel
import com.example.clientta.viewmodel.ProfissionalViewModel
import com.example.clientta.viewmodel.TratamentoViewModel
import com.example.clientta.viewmodel.ViewModelFactory

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val factory = ViewModelFactory(context)

    NavHost(
        navController = navController,
        startDestination = NavTarget.Login.route
    ) {
        composable(NavTarget.Login.route) {
            val loginViewModel: LoginViewModel = viewModel(factory = factory)
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccessCliente = {
                    navController.navigate(NavTarget.Inicio.route) {
                        popUpTo(NavTarget.Login.route) { inclusive = true }
                    }
                },
                onLoginSuccessProfissional = {
                    navController.navigate(NavTarget.AreaProfissional.route) {
                        popUpTo(NavTarget.Login.route) { inclusive = true }
                    }
                },
                onNavigateToCadastro = {
                    navController.navigate(NavTarget.Cadastro.route)
                }
            )
        }

        composable(NavTarget.Cadastro.route) {
            val cadastroViewModel: CadastroViewModel = viewModel(factory = factory)
            CadastroScreen(
                viewModel = cadastroViewModel,
                onCadastroSuccess = {
                    navController.navigate(NavTarget.Inicio.route) {
                        popUpTo(NavTarget.Login.route) { inclusive = true }
                    }
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(NavTarget.Inicio.route) {
            val tratamentoViewModel: TratamentoViewModel = viewModel(factory = factory)
            InicioScreen(
                tratamentoViewModel = tratamentoViewModel,
                onNavigateToTratamentos = { navController.navigate(NavTarget.Tratamentos.route) },
                onNavigateToAgendamentos = { navController.navigate(NavTarget.Agendamentos.route) },
                onNavigateToPerfil = { navController.navigate(NavTarget.Perfil.route) },
                onSelectTratamento = { id ->
                    navController.navigate(NavTarget.DetalheTratamento.createRoute(id))
                }
            )
        }

        composable(NavTarget.Tratamentos.route) {
            val tratamentoViewModel: TratamentoViewModel = viewModel(factory = factory)
            TratamentosScreen(
                viewModel = tratamentoViewModel,
                onSelectTratamento = { id ->
                    navController.navigate(NavTarget.DetalheTratamento.createRoute(id))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = NavTarget.DetalheTratamento.route,
            arguments = listOf(navArgument("tratamentoId") { type = NavType.LongType })
        ) { backStackEntry ->
            val tratamentoId = backStackEntry.arguments?.getLong("tratamentoId") ?: 0L
            val tratamentoViewModel: TratamentoViewModel = viewModel(factory = factory)
            DetalheTratamentoScreen(
                tratamentoId = tratamentoId,
                viewModel = tratamentoViewModel,
                onIniciarAgendamento = { id ->
                    navController.navigate(NavTarget.Agendar.createRoute(id))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = NavTarget.Agendar.route,
            arguments = listOf(navArgument("tratamentoId") { type = NavType.LongType })
        ) { backStackEntry ->
            val tratamentoId = backStackEntry.arguments?.getLong("tratamentoId") ?: 0L
            val agendamentoViewModel: AgendamentoViewModel = viewModel(factory = factory)
            val tratamentoViewModel: TratamentoViewModel = viewModel(factory = factory)
            AgendarScreen(
                tratamentoId = tratamentoId,
                agendamentoViewModel = agendamentoViewModel,
                tratamentoViewModel = tratamentoViewModel,
                onAgendamentoConcluido = {
                    navController.navigate(NavTarget.Agendamentos.route) {
                        popUpTo(NavTarget.Inicio.route)
                    }
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(NavTarget.Agendamentos.route) {
            val agendamentoViewModel: AgendamentoViewModel = viewModel(factory = factory)
            val tratamentoViewModel: TratamentoViewModel = viewModel(factory = factory)
            AgendamentosListScreen(
                agendamentoViewModel = agendamentoViewModel,
                tratamentoViewModel = tratamentoViewModel,
                onOpenPreAtendimento = { agendamentoId ->
                    navController.navigate(NavTarget.PreAtendimento.createRoute(agendamentoId))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = NavTarget.PreAtendimento.route,
            arguments = listOf(navArgument("agendamentoId") { type = NavType.LongType })
        ) { backStackEntry ->
            val agendamentoId = backStackEntry.arguments?.getLong("agendamentoId") ?: 0L
            val preAtendimentoViewModel: PreAtendimentoViewModel = viewModel(factory = factory)
            PreAtendimentoScreen(
                agendamentoId = agendamentoId,
                viewModel = preAtendimentoViewModel,
                onSalvarSuccess = { navController.popBackStack() },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(NavTarget.Perfil.route) {
            val perfilViewModel: PerfilViewModel = viewModel(factory = factory)
            PerfilScreen(
                viewModel = perfilViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(NavTarget.AreaProfissional.route) {
            val profissionalViewModel: ProfissionalViewModel = viewModel(factory = factory)
            AreaProfissionalScreen(
                viewModel = profissionalViewModel,
                onLogout = {
                    navController.navigate(NavTarget.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}
