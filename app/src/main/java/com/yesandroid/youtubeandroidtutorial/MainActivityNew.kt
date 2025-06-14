package com.yesandroid.youtubeandroidtutorial

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.yesandroid.youtubeandroidtutorial.db.AppDatabase
import com.yesandroid.youtubeandroidtutorial.db.User
import com.yesandroid.youtubeandroidtutorial.db.UserDao
import kotlinx.coroutines.launch

class MainActivityNew : AppCompatActivity() {

    private lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_new)

        val etName = findViewById<EditText>(R.id.etName)
        val etAge = findViewById<EditText>(R.id.etAge)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnLoad = findViewById<Button>(R.id.btnLoad)
        val tvUsers = findViewById<TextView>(R.id.tvUsers)

        val db = AppDatabase.getDatabase(this)
        userDao = db.userDao()

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString().toIntOrNull()

            if (name.isNotBlank() && age != null) {
                val user = User(name = name, age = age)
                lifecycleScope.launch {
                    userDao.insertUser(user)
                    Toast.makeText(this@MainActivityNew, "User saved", Toast.LENGTH_SHORT).show()
                    etName.text.clear()
                    etAge.text.clear()
                }
            } else {
                Toast.makeText(this, "Enter valid name and age", Toast.LENGTH_SHORT).show()
            }
        }

        btnLoad.setOnClickListener {
            lifecycleScope.launch {
                val users = userDao.getAllUsers()
                val userText = users.joinToString("\n") { "ID: ${it.id}, Name: ${it.name}, Age: ${it.age}" }
                tvUsers.text = userText.ifEmpty { "No users found." }
            }
        }

    }
}