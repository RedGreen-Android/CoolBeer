package com.example.cool_beers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cool_beers.common.BeerDI
import com.example.cool_beers.common.Router
import com.example.cool_beers.presentation.ui.screens.DetailBeerScreen
import com.example.cool_beers.presentation.ui.screens.ListBeerScreen
import com.example.cool_beers.presentation.ui.theme.CoolBeersTheme
import com.example.cool_beers.presentation.viewmodel.BeerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Router.List::class.java.simpleName
                ) {
                    composable(Router.List::class.java.simpleName) {
                        ListBeerScreen(
                            viewModel = BeerDI.provideViewModel(this@MainActivity),
                            navController = navController
                        )
                    }
                    composable(Router.Details().detailsRouter,
                        arguments = listOf(navArgument("beerId") {
                            type = NavType.IntType
                        })
                    ) {
                        DetailBeerScreen(
                            viewModel = BeerDI.provideViewModel(this@MainActivity),
                            beerId = it.arguments?.getInt("beerId", 0) ?: 0
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainApp(content: @Composable () -> Unit) {
    CoolBeersTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}