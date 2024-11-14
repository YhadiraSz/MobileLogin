package com.example.todolistperuvian

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    private lateinit var tilUsername: TextInputLayout
    private lateinit var tilDNI: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var etUsername: TextInputEditText
    private lateinit var etDNI: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnRegister: MaterialButton
    private lateinit var tvBackToLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initializeViews()
        setupWindowInsets()
        setupListeners()
    }

    private fun initializeViews() {
        tilUsername = findViewById(R.id.tilUsername)
        tilDNI = findViewById(R.id.tilDNI)
        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        etUsername = findViewById(R.id.etUsername)
        etDNI = findViewById(R.id.etDNI)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnRegister = findViewById(R.id.btnRegister)
        tvBackToLogin = findViewById(R.id.tvBackToLogin)
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupListeners() {
        btnRegister.setOnClickListener {
            registerUser()
        }

        tvBackToLogin.setOnClickListener {
            finish() // This will close the RegisterActivity and return to MainActivity
        }

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearErrors()
            }
        }

        etUsername.addTextChangedListener(textWatcher)
        etDNI.addTextChangedListener(textWatcher)
        etEmail.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)
    }

    private fun clearErrors() {
        tilUsername.error = null
        tilDNI.error = null
        tilEmail.error = null
        tilPassword.error = null
    }

    private fun registerUser() {
        val username = etUsername.text.toString()
        val dni = etDNI.text.toString()
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        if (validateInputs(username, dni, email, password)) {
            // Here you would typically implement the logic to register the user in your database or service
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
            // After successful registration, you might want to redirect the user to the login screen or main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateInputs(username: String, dni: String, email: String, password: String): Boolean {
        var isValid = true

        if (username.isEmpty()) {
            tilUsername.error = "Username is required"
            isValid = false
        }

        if (dni.isEmpty()) {
            tilDNI.error = "National ID is required"
            isValid = false
        }

        if (email.isEmpty()) {
            tilEmail.error = "Email is required"
            isValid = false
        }

        if (password.isEmpty()) {
            tilPassword.error = "Password is required"
            isValid = false
        }

        return isValid
    }
}