package com.droidper.xtrajob.core.navigation.navigationgraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.droidper.xtrajob.core.navigation.Screen

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(startDestination = Screen.Home.route, route = "home") {
        composable(route = Screen.Home.route) {

        }
        composable(route = Screen.NewDay.route) {

        }
        composable(route = Screen.Calendar.route) {

        }
        composable( route = Screen.RecordDays.route) {

        }
    }
}