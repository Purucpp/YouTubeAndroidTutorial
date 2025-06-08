@file:OptIn(ExperimentalMaterial3Api::class)

package com.yesandroid.youtubeandroidtutorial

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yesandroid.youtubeandroidtutorial.api.MyViewModel
import com.yesandroid.youtubeandroidtutorial.api.RawJsonViewModel
import com.yesandroid.youtubeandroidtutorial.ui.theme.YouTubeAndroidTutorialTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import androidx.lifecycle.viewmodel.compose.viewModel




// Main Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

       /* setContent {
            YouTubeAndroidTutorialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ApiScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }*/

        setContent {
            MaterialTheme {
                MyScreenWithToolbar()
            }
        }
    }
}



@Composable
fun ToastOnButtonClickScreen() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            Toast
                .makeText(context, "Button clicked!", Toast.LENGTH_SHORT)
                .show()
        }) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun MyScreenWithToolbar(viewModel: MyViewModel = viewModel()) {
    val context = LocalContext.current
    val result by viewModel.apiResult.collectAsState()

    LaunchedEffect(result) {
        result?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("My Toolbar", fontSize = 20.sp)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                viewModel.fetchData() // Call method, not invoke ViewModel

                Toast
                    .makeText(context, "Button clicked!", Toast.LENGTH_SHORT)
                    .show()
            }) {
                Text(text = "Click Me")
            }
        }
    }
}





