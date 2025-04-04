package com.example.dewanpannel.PanelActivity.SubActivitys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dewanpannel.Class.Addinformclass
import com.example.dewanpannel.R
import com.example.dewanpannel.databinding.ActivityUpdateDtBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class UpdateD_T : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateDtBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=  ActivityUpdateDtBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Updates  Directors to Teachers")
        storageReference = FirebaseStorage.getInstance().getReference("Updates  Directors to Teachers")
        binding.image.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
            intent.action = Intent.ACTION_PICK
            intent.type = "image/*"
            launcher.launch(intent)
        }

    }

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            if (it.data!!.data != null) {
                Picasso.get().load(it.data!!.data.toString()).placeholder(R.drawable.noticeboard).into(binding.image)

                storageReference.child("information").putFile(it.data!!.data!!).addOnSuccessListener {
                    storageReference.downloadUrl.addOnCompleteListener {

                    }
                }
            }

        }

        binding.send.setOnClickListener({
            if (binding.send.text.toString() == null) {
                Toast.makeText(this, "please select ", Toast.LENGTH_SHORT).show()
            } else {
                var userid = auth.currentUser?.uid
                var msg = binding.msg.text.toString()
                var image = binding.image.toString()
                val user = Addinformclass(msg, image)
                userid.let {
                    databaseReference.child("Information").setValue(user)
                        .addOnCompleteListener({
                            if (it.isSuccessful) {
                                Toast.makeText(this, "Sending meassage", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "somthing went wrong ", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        })
                }
            }
        })
    }
}