package com.example.timetable

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavBarDemo : AppCompatActivity() {
    lateinit var t:TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav_bar_demo)
        val nv=findViewById<BottomNavigationView>(R.id.bottomNav)
        t=findViewById(R.id.tv)
        nv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_favorites -> {
                    setContent("favorite")
                    true
                }
                R.id.menu_profile -> {
                    setContent("Profile")
                    true
                }

                R.id.nav_send -> {
                    setContent("Send")
                    true
                }
                else -> false
            }
        }
    }
    fun setContent(s:String)
    {
        setTitle(s)
        t.setText(s)
    }
}