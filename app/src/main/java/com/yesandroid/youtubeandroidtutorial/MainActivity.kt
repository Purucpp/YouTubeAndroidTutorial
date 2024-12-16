package com.yesandroid.youtubeandroidtutorial

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yesandroid.youtubeandroidtutorial.ui.theme.YouTubeAndroidTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YouTubeAndroidTutorialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SaveKeyValueScreen(
                        modifier = Modifier.padding(innerPadding),
                        sharedPreferences = getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE)
                    )
                }
            }
        }
    }
}

@Composable
fun SaveKeyValueScreen(modifier: Modifier = Modifier, sharedPreferences: SharedPreferences) {
    var key by remember { mutableStateOf("") }
    var value by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = key,
            onValueChange = { key = it },
            label = { Text("Key") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = value,
            onValueChange = { value = it },
            label = { Text("Value") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (key.isNotBlank() && value.isNotBlank()) {
                    sharedPreferences.edit().putString(key, value).apply()
                    message = "Saved! Key: $key, Value: $value"
                } else {
                    message = "Please enter both key and value."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
        if (message.isNotEmpty()) {
            Text(text = message, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SaveKeyValueScreenPreview() {
    YouTubeAndroidTutorialTheme {
        SaveKeyValueScreen(sharedPreferences = FakeSharedPreferences())
    }
}

// A fake SharedPreferences implementation for preview purposes
class FakeSharedPreferences : SharedPreferences {
    private val data = mutableMapOf<String, String>()

    override fun getAll(): MutableMap<String, *> = data

    override fun getString(key: String?, defValue: String?): String? = data[key] ?: defValue

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? = null

    override fun getInt(key: String?, defValue: Int): Int = 0

    override fun getLong(key: String?, defValue: Long): Long = 0

    override fun getFloat(key: String?, defValue: Float): Float = 0f

    override fun getBoolean(key: String?, defValue: Boolean): Boolean = false

    override fun contains(key: String?): Boolean = data.containsKey(key)

    override fun edit(): SharedPreferences.Editor = FakeEditor(data)

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {}

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {}

    class FakeEditor(private val data: MutableMap<String, String>) : SharedPreferences.Editor {
        override fun putString(key: String?, value: String?): SharedPreferences.Editor {
            if (key != null && value != null) data[key] = value
            return this
        }

        override fun putStringSet(key: String?, values: MutableSet<String>?): SharedPreferences.Editor = this

        override fun putInt(key: String?, value: Int): SharedPreferences.Editor = this

        override fun putLong(key: String?, value: Long): SharedPreferences.Editor = this

        override fun putFloat(key: String?, value: Float): SharedPreferences.Editor = this

        override fun putBoolean(key: String?, value: Boolean): SharedPreferences.Editor = this

        override fun remove(key: String?): SharedPreferences.Editor {
            if (key != null) data.remove(key)
            return this
        }

        override fun clear(): SharedPreferences.Editor {
            data.clear()
            return this
        }

        override fun commit(): Boolean = true

        override fun apply() {}
    }
}
