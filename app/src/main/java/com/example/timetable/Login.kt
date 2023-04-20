package com.example.timetable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

@Suppress("DEPRECATION")
class Login : AppCompatActivity() {
    private fun showCustomToast(message: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, null)

        val toastText = layout.findViewById<TextView>(R.id.custom_toast_text)
        toastText.text = message

        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.BOTTOM, 0, 150)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            val user : EditText = findViewById(R.id.login_username)
            val pass: EditText = findViewById(R.id.login_password)
//            val intent = Intent(this, TimetableActivity::class.java)
//            startActivity(intent)
            val username: String = user.text.toString()
            val password: String = pass.text.toString()

            if(username == "admin" && password == "admin") {
                val intent = Intent(this, TimetableActivity::class.java)
                startActivity(intent)
            }
            else if(username == "student" && password == "student")
            {
                val intent = Intent(this, TimetableActivity::class.java)
                startActivity(intent)
            }
            else {
                showCustomToast("Unable to login")
            }

        }

    }
}