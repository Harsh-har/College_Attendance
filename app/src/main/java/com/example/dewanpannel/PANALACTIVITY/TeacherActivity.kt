package com.example.dewanpannel.PANALACTIVITY

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.dewanpannel.PanelActivity.SubActivitys.TimetableActivity
import com.example.dewanpannel.PanelActivity.SubActivitys.UpdateT_All
import com.example.dewanpannel.PanelActivity.SubActivitys.UpdateT_S
import com.example.dewanpannel.R
import com.example.dewanpannel.databinding.ActivityTeacherBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class TeacherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeacherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityTeacherBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addnoticeteacher.setOnClickListener({
            startActivity(Intent(this, UpdateT_All::class.java))
        })
        binding.imageView7.setOnClickListener({
            startActivity(Intent(this, UpdateT_S::class.java))
        })
         binding.imageView5.setOnClickListener({
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://erp.aktu.ac.in/login.aspx")))
        })
 binding.classtimeupdate.setOnClickListener({
     startActivity(Intent(this,TimetableActivity::class.java))
 })
        binding.nav.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.director->
                        Toast.makeText(this@TeacherActivity, "hello students", Toast.LENGTH_SHORT).show()

                    R.id.student->{
                        Toast.makeText(this@TeacherActivity, "hello students", Toast.LENGTH_SHORT).show()
                    }
                    R.id.result->{
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://oneview.aktu.ac.in/WebPages/aktu/OneView.aspx")))
                    }
                    R.id.logout->{
                        Toast.makeText(this@TeacherActivity, "Log out is Successfull", Toast.LENGTH_SHORT).show()
                        Firebase.auth.signOut()
                        finishAffinity()
                    }
                    else-> ""

                }


                return true
            }

        })
    }
}