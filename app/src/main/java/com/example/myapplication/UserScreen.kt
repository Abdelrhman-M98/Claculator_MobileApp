package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.myapplication.databinding.UserScreenBinding

class UserScreen : AppCompatActivity() {

    private lateinit var binding: UserScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.submitButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()

            // Create an intent to launch the Calculator activity
            val intent = Intent(this, Calculator::class.java)

            // Pass the name as an extra to the intent
            intent.putExtra("name", name)

            // Start the Calculator activity
            startActivity(intent)
        }
    }
}
