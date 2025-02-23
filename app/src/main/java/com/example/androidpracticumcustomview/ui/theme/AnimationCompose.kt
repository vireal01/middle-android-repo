package com.example.androidpracticumcustomview.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

/*
Задание:
Реализуйте необходимые компоненты.
*/

@Composable
fun AnimationCompose() {
  Scaffold { paddingValues ->
    CustomContainerCompose(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues),
      firstChild = {
        Text(text = "First", style = TextStyle(fontSize = 24.sp))
      },
      secondChild = {
        Text("Second", style = TextStyle(fontSize = 24.sp))
      }
    )
  }
}