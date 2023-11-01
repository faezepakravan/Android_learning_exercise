package com.example.exercideonereggistration.navigaton

sealed class Screen(val rout: String) {
    object FirstScreen : Screen("firstScreen")
    object SecondScreen : Screen("secondScreen")
}
