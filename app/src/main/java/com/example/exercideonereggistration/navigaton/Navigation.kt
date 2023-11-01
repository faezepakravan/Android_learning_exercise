package com.example.exercideonereggistration.navigaton

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()

//    NavHost(navController, startDestination = Screen.FirstScreen.rout) {
//        composable(Screen.FirstScreen.rout) { FirstPage(navController) }
//        //composable(Screen.SecondScreen.rout) { SecondPage() }
//    }
}