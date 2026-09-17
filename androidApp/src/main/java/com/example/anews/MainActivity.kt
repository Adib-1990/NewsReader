package com.example.anews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.anews.ui.NewsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Empty key = offline demo mode with built-in sample news (works without internet).
        // Put a real NewsAPI key here to fetch live headlines instead.
        val apiKey = ""

        // Theme (light/dark), language (EN/FA + RTL) are handled inside NewsScreen.
        setContent {
            NewsScreen(apiKey = apiKey)
        }
    }
}
