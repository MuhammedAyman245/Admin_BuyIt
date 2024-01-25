package com.example.admin_buyit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.admin_buyit.screens.activities.HomeActivity
import com.example.admin_buyit.screens.registration.LogInActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            if(auth.currentUser == null){
                val intent = Intent(applicationContext, LogInActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent) }
        },2000)
    }
}