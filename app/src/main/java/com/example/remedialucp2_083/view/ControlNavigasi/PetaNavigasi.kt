package com.example.remedialucp2_083.view.ControlNavigasi

import androidx.compose.runtime.Composable
import com.example.remedialucp2_083.view.route.DestinasiDetail
import com.example.remedialucp2_083.view.route.DestinasiEntry
import com.example.remedialucp2_083.view.route.DestinasiHome

@Composable
fun DataBukuApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    HostNavigasiBuku(navController = navController)
}

@Composable
fun HostNavigasiBuku(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(DestinasiHome.route) {
            HomeBukuScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                navigateToItemUpdate = {
                    navController.navigate("${DestinasiDetail.route}/$it")
                }
            )
        }

        composable(DestinasiEntry.route) {
            EntryBukuScreen(
                navigateBack = { navController.navigate(DestinasiHome.route) }
            )
        }

        composable(
            DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.itemIdArg) {
                    type = NavType.StringType
                }
            )
        ) {
            DetailBukuScreen(
                navigateToEditItem = { navController.navigate("${DestinasiEdit.route}/$it") },
                navigateBack = { navController.navigate(DestinasiHome.route) }
            )
        }

        composable(
            DestinasiEdit.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiEdit.itemIdArg) {
                    type = NavType.StringType
                }
            )
        ) {
            EditBukuScreen(
                navigateBack = { navController.navigate(DestinasiHome.route) },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}