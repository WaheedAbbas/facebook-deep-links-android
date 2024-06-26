package com.example.facebook_deep_links_android

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.facebook_deep_links_android.ui.theme.FacebookdeeplinksandroidTheme
import com.facebook.applinks.AppLinkData
import com.facebook.applinks.AppLinkData.createFromActivity

class MainActivity : ComponentActivity() {

    private var greetingText by mutableStateOf("Android")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FacebookdeeplinksandroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = greetingText,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        // Check for deep links when activity is created
        checkDeepLinks()
    }

    private fun checkDeepLinks() {
        // Check if the app was opened from a deep link
        val appLinkData: AppLinkData? = createFromActivity(this)

        if (appLinkData != null && appLinkData.targetUri != null) {
            // Handle deep link URL, extract parameters, and navigate to relevant content
            greetingText = "Android - launched via web deep links"
        } else {
            // Handle the case where the app was opened without a deep link
            greetingText = "Android"
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FacebookdeeplinksandroidTheme {
        Greeting("Android")
    }
}