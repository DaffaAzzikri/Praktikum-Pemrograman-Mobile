package com.example.modul3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modul3.ui.theme.Modul3Theme
import com.example.modul3.ui.CharsDetailScreen
import com.example.modul3.ui.CharsListScreen
import com.example.modul3.ui.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val vm: CharsViewModel = viewModel()

                    NavHost(
                        navController = navController,
                        startDestination = Routes.List,
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        composable(Routes.List) {
                            CharsListScreen(
                                featured = vm.featuredChars,
                                all = vm.allChars,
                                onOpenDetail = { id -> navController.navigate(Routes.detail(id)) },
                            )
                        }
                        composable(Routes.DetailPattern) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id").orEmpty()
                            val char = vm.getCharById(id)
                            CharsDetailScreen(
                                char = char,
                                onBack = { navController.popBackStack() },
                            )
                        }
                    }
                }
            }
        }
    }
}