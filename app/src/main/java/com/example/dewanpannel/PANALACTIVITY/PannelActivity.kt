package com.example.dewanpannel.PANALACTIVITY

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dewanpannel.R
import com.example.dewanpannel.databinding.ActivityPannelBinding
import com.google.firebase.auth.FirebaseAuth

class PannelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPannelBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPannelBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            startActivity(Intent(this,TeacherActivity::class.java))
          finishAffinity()
        }

            val imageList = ArrayList<SlideModel>()
            imageList.add(SlideModel(R.drawable.fefef))
            imageList.add(SlideModel(R.drawable.two))
            imageList.add(SlideModel(R.drawable.three))

            val imageSlider = binding.imageslider
            imageSlider.setImageList(imageList)

            binding.imageView4.setOnClickListener({
                startActivity(Intent(this, PanelLogin::class.java))
            })

            binding.imageView7.setOnClickListener({
                startActivity(Intent(this, PanelLogin::class.java))
            })

            binding.imageView6.setOnClickListener({
                startActivity(Intent(this, PanelLogin::class.java))
            })
            binding.imageView5.setOnClickListener({
                startActivity(Intent(this, PanelLogin::class.java))
            })
        }
    }
