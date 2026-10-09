package com.example.clientta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.clientta.ui.navigation.NavGraph
import com.example.clientta.ui.theme.ClienttaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ClienttaTheme {
                NavGraph()
            }
        }
    }
}