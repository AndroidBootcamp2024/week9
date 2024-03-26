package com.kodeco.android.countryinfo.ui.screens.countryinfo


import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Info


import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.kodeco.android.countryinfo.repositories.CountryRepository
import com.kodeco.android.countryinfo.ui.components.AboutCountryApp
import com.kodeco.android.countryinfo.ui.screens.countrydetails.CountryDetailsScreen
import com.kodeco.android.countryinfo.ui.screens.countrydetails.CountryDetailsViewModel


@Composable
fun CountryInfoNavHost(
    repository: CountryRepository,
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Country App")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("list") }) {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Filled.House),
                            contentDescription = "Menu"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("about")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "About"
                        )
                    }
                }
            )
        }
    ) { padding ->
        NavHost(navController = navController, startDestination = "list") {
            composable("list") {
                CountryInfoScreen(
                    viewModel = viewModel(
                        factory = CountryInfoViewModel.CountryInfoViewModelFactory(
                            repository = repository
                        ),
                    ),
                    onCountryRowTap = { countryIndex: Int ->
                        navController.navigate("details/$countryIndex")
                    }
                )
            }
            composable("details/{idCountry}") {
                val id = it.arguments?.getString("idCountry")?.toInt()

                CountryDetailsScreen(
                    viewModel = viewModel(
                        factory = CountryDetailsViewModel.CountryDetailsViewModelFactory(
                            repository = repository,
                            countryId = id ?: padding.toString().toInt()
                        ),
                    ),
                    onNavigateUp = {
                        navController.navigate("list")
                    }
                )
            }
            composable("about") {
                AboutCountryApp()
            }
        }
    }

}
