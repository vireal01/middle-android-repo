package com.example.androidpracticumcustomview

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidpracticumcustomview.ui.theme.AnimationCompose
import com.example.androidpracticumcustomview.ui.theme.AnimationXml
import com.example.androidpracticumcustomview.ui.theme.MainScreen

/*
Задание:
Реализуйте необходимые компоненты.
*/

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AppNavHost()
    }
  }
}

@Composable
fun AppNavHost() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = "mainScreen"
  ) {
    composable("mainScreen") {
      MainScreen(
        onGoToXml = { navController.context.startActivity(Intent(navController.context, AnimationXml::class.java)) },
        onGoToCompose = { navController.navigate("animationCompose") }
      )
    }
    composable("animationCompose") {
      AnimationCompose()
    }
  }
}