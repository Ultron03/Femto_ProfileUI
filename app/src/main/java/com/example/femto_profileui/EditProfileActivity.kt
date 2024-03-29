package com.example.femto_profileui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class EditProfileActivity : AppCompatActivity() {
    private lateinit var back_to_profile:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        init()

        back_to_profile.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
    private fun init(){
        back_to_profile = findViewById(R.id.back_to_profile)
    }
}