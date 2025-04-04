package com.example.dewanpannel.PANALACTIVITY

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dewanpannel.PanelActivity.SubActivitys.UpdateTpo_All
import com.example.dewanpannel.PanelActivity.SubActivitys.UpdateTpo_S
import com.example.dewanpannel.databinding.ActivityTpoBinding

class TpoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTpoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityTpoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
          binding.imageView4.setOnClickListener({
            startActivity(Intent(this, UpdateTpo_All::class.java))
        })
        binding.imageView6.setOnClickListener({
            startActivity(Intent(this, UpdateTpo_S::class.java))
        })
    }
}