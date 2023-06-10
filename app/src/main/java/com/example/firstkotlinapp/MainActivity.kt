package com.example.firstkotlinapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val date = findViewById<TextView>(R.id.textView)
        val minutesDisplay = findViewById<TextView>(R.id.textView3)
        val hoursDisplay = findViewById<TextView>(R.id.textView5)


        fun showDatePicker() {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)+1
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Allow user to select Date
            val selectDateDialog = DatePickerDialog(this, dateSetListener, year, month, day)
            selectDateDialog.show()
        }

         dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
           var selectedDate = "$year/$month/$dayOfMonth"
            date.text = selectedDate
             //  get the date format that will help in subtraction
             var dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
             var currentDate = Date()

             var parsedDate = dateFormat.parse(selectedDate)
             var timeDifferenceInMs = currentDate.time - parsedDate.time


             var timeDifferemceInMin = timeDifferenceInMs/60000
             var timeDifferenceInHrs = timeDifferemceInMin/60

             minutesDisplay.text = (timeDifferemceInMin).toString()
             hoursDisplay.text = timeDifferenceInHrs.toString()


        }

        date.setOnClickListener {
            showDatePicker()
        }





    }
}
