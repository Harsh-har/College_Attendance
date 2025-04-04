package com.example.dewanpannel.PANALACTIVITY

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.dewanpannel.PanelActivity.SubActivitys.TimetableActivity
import com.example.dewanpannel.PanelActivity.SubActivitys.UpdateS_T
import com.example.dewanpannel.R
import com.example.dewanpannel.StudentTeacher
import com.example.dewanpannel.databinding.ActivityStudentBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class StudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityStudentBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


       binding.imageView4.setOnClickListener({
            startActivity(Intent(this, UpdateS_T::class.java))

        })

        binding.imageView7.setOnClickListener({
           startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://erp.aktu.ac.in/login.aspx")))
        })

        binding.imageView5.setOnClickListener({
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://thearsolution.com/")))

        })
       binding.timetable.setOnClickListener({
        startActivity(Intent(this,TimetableActivity::class.java))
        })
        binding.menunav.setOnClickListener({

        })

        binding.nav.setNavigationItemSelectedListener(object :NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
          when(item.itemId){
              R.id.director->
                  Toast.makeText(this@StudentActivity, "hello students", Toast.LENGTH_SHORT).show()

              R.id.teacher->{

                  startActivity(Intent(this@StudentActivity,StudentTeacher::class.java))
              }
                  R.id.tpo->
                  Toast.makeText(this@StudentActivity, "hello students", Toast.LENGTH_SHORT).show()
              R.id.result->{
                  startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://oneview.aktu.ac.in/WebPages/aktu/OneView.aspx")))
              }
              R.id.logout->{
                  Toast.makeText(this@StudentActivity, "Log out is Successfull", Toast.LENGTH_SHORT).show()
                   Firebase.auth.signOut()


              }
                  else-> ""

          }


             return true
            }

        })

    }
}