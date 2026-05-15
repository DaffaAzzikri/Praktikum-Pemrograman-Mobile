package com.example.modul3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modul3.ui.CharsDetailScreen
import com.example.modul3.ui.CharsListScreen
import com.example.modul3.ui.Routes
import com.example.modul3.ui.openUrl
import com.example.modul3.ui.theme.Modul3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val vm: CharsViewModel = viewModel(
                        factory = CharsViewModelFactory(getString(R.string.app_name)),
                    )

                    val context = LocalContext.current
                    val featured by vm.featuredChars.collectAsStateWithLifecycle()
                    val all by vm.allChars.collectAsStateWithLifecycle()
                    val detailTargetId by vm.detailTargetId.collectAsStateWithLifecycle()
                    val wikiUrl by vm.wikiUrl.collectAsStateWithLifecycle()

                    LaunchedEffect(detailTargetId) {
                        val id = detailTargetId ?: return@LaunchedEffect
                        navController.navigate(Routes.detail(id))
                        vm.consumeDetailNavigation()
                    }

                    LaunchedEffect(wikiUrl) {
                        val url = wikiUrl ?: return@LaunchedEffect
                        context.openUrl(url)
                        vm.consumeWikiNavigation()
                    }

                    NavHost(
                        navController = navController,
                        startDestination = Routes.List,
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        composable(Routes.List) {
                            CharsListScreen(
                                featured = featured,
                                all = all,
                                onDetailClick = vm::onDetailClick,
                                onExplicitIntentClick = vm::onExplicitIntentClick,
                            )
                        }
                        composable(Routes.DetailPattern) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id").orEmpty()
                            val char = vm.getCharById(id)
                            CharsDetailScreen(
                                char = char,
                                onBack = { navController.popBackStack() },
                                onExplicitIntentClick = { clicked ->
                                    vm.onExplicitIntentClick(clicked)
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}
