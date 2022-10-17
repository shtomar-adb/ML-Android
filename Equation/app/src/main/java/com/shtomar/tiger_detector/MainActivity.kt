package com.shtomar.tiger_detector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.google.firebase.FirebaseApp
import com.shtomar.tiger_detector.model.ModelDownloader
import com.shtomar.tiger_detector.ui.theme.Tiger_DetectorTheme
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tiger_DetectorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }

        GlobalScope.launch {
            FirebaseApp.initializeApp(this@MainActivity)
            withContext(Dispatchers.Default){
                ModelDownloader.downloadModel()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Tiger_DetectorTheme {
        Greeting("Android")
    }
}