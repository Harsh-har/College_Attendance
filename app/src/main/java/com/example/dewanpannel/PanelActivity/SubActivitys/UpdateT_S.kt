package com.example.dewanpannel.PanelActivity.SubActivitys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dewanpannel.Class.Addinformclass
import com.example.dewanpannel.R
import com.example.dewanpannel.databinding.ActivityUpdateTsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class UpdateT_S : AppCompatActivity() {
    private lateinit var binding:ActivityUpdateTsBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateTsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Updates  Teachers to Students")

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
                    databaseReference.child("Information" + System.currentTimeMillis() +"." +getFileType(it)).setValue(user)
                        .addOnCompleteListener({
                            if (it.isSuccessful) {
                                Toast.makeText(this, "Sending meassage", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "somthing went wrong ", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            binding.msg.setText("")
                        })
                }
            }
        })
    }

    private fun getFileType(data: String?): Any? {
val mineType=MimeTypeMap.getSingleton()
        return mineType.getMimeTypeFromExtension(contentResolver.getType(data!!))
    }
}




