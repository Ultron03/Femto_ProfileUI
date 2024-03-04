package com.example.femto_profileui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    private lateinit var editProfile:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        editProfile.setOnClickListener {
            startActivity(Intent(this,EditProfileActivity::class.java))
        }
    }
    private fun init(){
        editProfile = findViewById(R.id.edit_profile)
    }
}
