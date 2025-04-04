package com.example.dewanpannel.PANALACTIVITY

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed
import com.example.dewanpannel.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(1000){
            startActivity(Intent(this, PannelActivity::class.java))
            finish()
        }
    }
}