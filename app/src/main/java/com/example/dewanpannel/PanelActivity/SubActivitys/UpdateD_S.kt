package com.example.dewanpannel.PanelActivity.SubActivitys

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dewanpannel.Class.Addinformclass
import com.example.dewanpannel.R
import com.example.dewanpannel.databinding.ActivityUpdateDsBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class UpdateD_S : AppCompatActivity() {
    private lateinit var binding:ActivityUpdateDsBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateDsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Updates  Directors to Students")
        storageReference = FirebaseStorage.getInstance().getReference("Updates  Directors to Students")
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

                storageReference.child("information" + System.currentTimeMillis()+ "." +getFileType(it.data!!.data)).putFile(it.data!!.data!!).addOnSuccessListener {

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
                    databaseReference.child("Photos").setValue(user)
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

    private fun getFileType(data: Uri?): String? {
        val r=contentResolver
var type=MimeTypeMap.getSingleton()
        return type.getMimeTypeFromExtension(r.getType(data!!))
    }
}