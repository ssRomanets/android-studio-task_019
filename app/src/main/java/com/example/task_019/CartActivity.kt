package com.example.task_019

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

import java.time.LocalDate
import java.time.Period
import kotlin.system.exitProcess

class CartActivity : AppCompatActivity() {

    private lateinit var toolbarMain: Toolbar

    private lateinit var imageViewIV: ImageView
    private lateinit var firstNameET: EditText
    private lateinit var lastNameET: EditText
    private lateinit var phoneET: EditText
    private lateinit var outputDateInfoET: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Карточка пользователя."

        init()

        val person: Person = intent.extras?.getSerializable("person") as Person

        imageViewIV.setImageURI(Uri.parse(person.image))
        firstNameET.setText(person.firstName)
        lastNameET.setText(person.lastName)
        phoneET.setText(person.phone)

        val listDateListStr = person.dateOfBirth.split('.').toMutableList()

        if (listDateListStr.size == 3)
        {
            val date1 = LocalDate.of(
                listDateListStr.get(0).toInt(),listDateListStr.get(1).toInt(),listDateListStr.get(2).toInt()
            )
            val date2 = LocalDate.now()
            val period = Period.between(date2, date1)
            outputDateInfoET.setText("лет - " + period.years.toString() +
                                     " месяцев - " + period.months.toString() +
                                     " дней - " + period.days.toString())
        }

    }

    fun init() {
        imageViewIV = findViewById(R.id.imageViewIV)
        firstNameET = findViewById(R.id.firstNameET)
        lastNameET = findViewById(R.id.lastNameET)
        phoneET = findViewById(R.id.phoneET)
        outputDateInfoET = findViewById(R.id.outputDateInfoET)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_menu, menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain->{
                moveTaskToBack(true);
                exitProcess(-1)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}

