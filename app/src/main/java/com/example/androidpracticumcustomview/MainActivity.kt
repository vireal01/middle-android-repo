package com.example.androidpracticumcustomview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.androidpracticumcustomview.ui.theme.CustomContainer

/*
Задание:
Реализуйте необходимые компоненты.
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        Раскомментируйте нужный вариант
         */
        startXmlPracticum() // «традиционный» android (XML)
//          setContent { // Jetpack Compose
//             MainScreen()
    }

    private fun startXmlPracticum() {
        val customContainer = CustomContainer(context = this, animationDuration = 2_000L)
        setContentView(customContainer)

        val firstView = TextView(this).apply {
            text = "first"
            textSize = 24f
        }

        val secondView = TextView(this).apply {
            text = "second"
            textSize = 24f
        }


        val third = TextView(this).apply {
            text = "third"
            textSize = 24f
        }

        customContainer.addView(firstView)

        // Добавление второго элемента через некоторое время
        Handler(Looper.getMainLooper()).postDelayed({
            customContainer.addView(secondView)
        }, 2000)
    }
}