package com.example.dewanpannel.PANALACTIVITY

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.dewanpannel.Class.Dataclass
import com.example.dewanpannel.databinding.PanelloginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PanelLogin : AppCompatActivity() {
    private lateinit var binding: PanelloginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = PanelloginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        var Panel = binding. Panel
        val arraystate = arrayOf("Directors", "Teachers", "Tpo", "Students")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, arraystate)
        Panel.adapter = adapter

        Panel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var select = arraystate[position]
                Toast.makeText(this@PanelLogin, "You select $select", Toast.LENGTH_SHORT).show()

                var username = binding.username.text.toString()
                var email = binding.email.text.toString()
                var password = binding.password.text.toString()
                binding.button.setOnClickListener({

                    if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(this@PanelLogin, "Please Enter all Details🙂", Toast.LENGTH_SHORT).show()
                    }
                    else {

                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this@PanelLogin) { task ->
                                val userId = auth.currentUser?.uid
                                val user = Dataclass(username, email ,select)
                                userId?.let {
                                    databaseReference.child("Data").setValue(user)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful)
                                            {
                                                Toast.makeText(this@PanelLogin, "Log in successfull", Toast.LENGTH_LONG).show()
                                            }
                                            else
                                            {
                                                Toast.makeText(this@PanelLogin, "Failed to register user", Toast.LENGTH_SHORT).show()
                                            }
                                        }

                                }
                            }



                    if (select == arraystate[0]) {
                        val intent = Intent(this@PanelLogin, DirectorsActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else if (select == arraystate[1]) {
                        startActivity(Intent(this@PanelLogin, TeacherActivity::class.java))
                        finish()
                    } else if (select == arraystate[2]) {
                        startActivity(Intent(this@PanelLogin, TpoActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this@PanelLogin, StudentActivity::class.java))
                        finish()
                    }

                    }

                })


            }


            override fun onNothingSelected(parent: AdapterView<*>?) {


            }
        }
    }
}

