package com.example.clientta.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import com.example.clientta.ui.theme.ClienttaPrimary
import com.example.clientta.ui.theme.ClienttaPrimaryLight
import com.example.clientta.ui.theme.ClienttaWhite

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Tratamentos : BottomNavItem("tratamentos", "Tratamentos", Icons.Default.Face)
    data object Agendamentos : BottomNavItem("agendamentos", "Agenda", Icons.Default.DateRange)
    data object Historico : BottomNavItem("historico", "Histórico", Icons.Default.Refresh)
    data object Perfil : BottomNavItem("perfil", "Perfil", Icons.Default.Person)
}

@Composable
fun BottomNavBar(
    currentRoute: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem.Tratamentos,
        BottomNavItem.Agendamentos,
        BottomNavItem.Historico,
        BottomNavItem.Perfil
    )

    NavigationBar(
        modifier = modifier,
        containerColor = ClienttaWhite
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemSelected(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = ClienttaPrimary
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 12.sp,
                        color = ClienttaPrimary
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = ClienttaPrimaryLight
                )
            )
        }
    }
}
