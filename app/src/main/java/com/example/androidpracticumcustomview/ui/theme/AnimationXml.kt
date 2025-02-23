package com.example.androidpracticumcustomview.ui.theme;

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.ComponentActivity

class AnimationXml: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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


        val thirdView = TextView(this).apply {
            text = "third"
            textSize = 24f
        } // для проверки ошибки добавления третьего элемента

        customContainer.addView(firstView)

        Handler(Looper.getMainLooper()).postDelayed({
            customContainer.addView(secondView)
        }, 2000)
    }
}