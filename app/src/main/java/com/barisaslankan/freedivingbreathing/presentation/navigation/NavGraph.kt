package com.barisaslankan.freedivingbreathing.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun SetUpNavGraph(
    startDestination : String,
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){


    }
}

fun NavGraphBuilder.exerciseRoute(){

}