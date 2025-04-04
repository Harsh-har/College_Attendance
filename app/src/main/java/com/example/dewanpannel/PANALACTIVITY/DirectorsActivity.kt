package com.example.dewanpannel.PANALACTIVITY

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.dewanpannel.PanelActivity.SubActivitys.UpdateD_All
import com.example.dewanpannel.PanelActivity.SubActivitys.UpdateD_S
import com.example.dewanpannel.PanelActivity.SubActivitys.UpdateD_T
import com.example.dewanpannel.R
import com.example.dewanpannel.databinding.ActivityDirectorsBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class DirectorsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDirectorsBinding
            override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityDirectorsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

                binding.addnotice.setOnClickListener({
                startActivity(Intent(this,UpdateD_All::class.java))
                })

                binding.imageView7.setOnClickListener({
                    startActivity(Intent(this,UpdateD_T::class.java))
                })

                binding.imageView6.setOnClickListener({
                    startActivity(Intent(this,UpdateD_S::class.java))
                })
                binding.erp.setOnClickListener({
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://erp.aktu.ac.in/login.aspx")))
                })
                binding.nav.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener{
                    override fun onNavigationItemSelected(item: MenuItem): Boolean {
                        when(item.itemId){
                            R.id.student->
                                Toast.makeText(this@DirectorsActivity, "hello students", Toast.LENGTH_SHORT).show()

                            R.id.teacher->{
                                Toast.makeText(this@DirectorsActivity, "hello students", Toast.LENGTH_SHORT).show()
                            }
                            R.id.result->{
                                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://oneview.aktu.ac.in/WebPages/aktu/OneView.aspx")))
                            }
                            R.id.logout->{
                                Toast.makeText(this@DirectorsActivity, "Log out is Successfull", Toast.LENGTH_SHORT).show()
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