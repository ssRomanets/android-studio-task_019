package com.example.task_019

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    var person: Person? = null
    private val GALLERY_REQUEST = 382
    var photoUri: Uri? = null

    private lateinit var editImageIV: ImageView

    private lateinit var firstNameET: EditText
    private lateinit var lastNameET: EditText
    private lateinit var dateOfBirthET: EditText
    private lateinit var phoneET: EditText

    private lateinit var saveBTN: Button

//    private lateinit var editDateET: EditText
//    private lateinit var textDateTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        editImageIV.setOnClickListener{
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST )
        }

        saveBTN.setOnClickListener{
            val personImage       = photoUri.toString()
            val personFirstName   = firstNameET.text.toString()
            val personLastName    = lastNameET.text.toString()
            val personDateOfBirth = dateOfBirthET.text.toString()
            val personPhone       = phoneET.text.toString()

            val person = Person(
                personImage,
                personFirstName,
                personLastName,
                personDateOfBirth,
                personPhone
            )

            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("person", person)
            startActivity(intent)

            clearEditFields()
            photoUri = null
        }

//        editDateET = findViewById(R.id.editDateET)
//        textDateTV = findViewById(R.id.textDateTV)
//
//        val currentDate = Date()
//        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
//        val dateText = dateFormat.format(currentDate)

//        textDateTV.setText(dateText)
    }

    private fun init() {
        editImageIV = findViewById(R.id.editImageIV)
        firstNameET = findViewById(R.id.firstNameET)
        lastNameET = findViewById(R.id.lastNameET)
        dateOfBirthET = findViewById(R.id.dateOfBirthET)
        phoneET = findViewById(R.id.phoneET)
        saveBTN = findViewById(R.id.saveBTN)
    }

    private fun clearEditFields() {
        editImageIV.setImageResource(R.drawable.person_ic)
        firstNameET.text.clear()
        lastNameET.text.clear()
        dateOfBirthET.text.clear()
        phoneET.text.clear()
    }

    override fun onActivityResult(requestedCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestedCode, resultCode, data)
        editImageIV = findViewById(R.id.editImageIV)
        when (requestedCode) {
            GALLERY_REQUEST -> if (resultCode === RESULT_OK) {
                photoUri = data?.data
                editImageIV.setImageURI(photoUri)
            }
        }
    }
}