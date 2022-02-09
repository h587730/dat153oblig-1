package edu.stanford.rkpandey.rvpractice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.item_person.*

private const val TAG = "openGalleryForImage"

class ContactsActivity : AppCompatActivity() {

    private lateinit var btnSelectImage : Button
    private lateinit var ivPreviewimage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        btnSubmit.setOnClickListener { 
            val name = etName.text.toString()
            val age = etAge.text.toString().toInt()
            val newPerson = Person(name, age)
            val output = Intent()
            output.putExtra("person", newPerson)
            setResult(Activity.RESULT_OK, output)
            finish()
        }

        btnSelectImage = findViewById(R.id.btnSelectImage)
        ivPreviewimage = findViewById(R.id.IvPreviewImage)

        btnSelectImage.setOnClickListener{
            openGalleryForImage()
            Log.i(TAG, "Gallery Opened")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 32){
            ivPreviewimage.setImageURI(data?.data)
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 32)
    }
}