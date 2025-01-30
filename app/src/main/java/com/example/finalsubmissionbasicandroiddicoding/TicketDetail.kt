package com.example.finalsubmissionbasicandroiddicoding

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AppCompatActivity

class TicketDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_detail)

        supportActionBar?.apply {
            title = getString(R.string.airlines_detail)

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(true)
        }

        val ticketDetail = intent.getParcelableExtra<Airlines>(MainActivity.INTENT_PARCELABLE)

        if (ticketDetail == null) {
            finish()
            return
        }

        val includeView = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.include_payment_method)
        val spinner = includeView.findViewById<Spinner>(R.id.payment_method_spinner)

        val photo = findViewById<ImageView>(R.id.photo_img)
        val name = findViewById<TextView>(R.id.ticket_title)
        val book = findViewById<TextView>(R.id.book_date)
        val detail = findViewById<TextView>(R.id.description)
        val paymentMethod = resources.getStringArray(R.array.payment_method)
        val flightDuration = findViewById<TextView>(R.id.flight_duration)
        val flightRoute = findViewById<TextView>(R.id.flight_route)
        val bookingInfo = findViewById<TextView>(R.id.booking_info)

        photo.setImageResource(ticketDetail?.photo!!)
        name.text = ticketDetail.name
        book.text= ticketDetail.book
        detail.text= ticketDetail.detail
        flightDuration.text = getString(R.string.flight_duration)
        flightRoute.text = getString(R.string.flight_route)
        bookingInfo.text = getString(R.string.booking_instructions)

        Log.d("TicketDetail", "Payment methods: ${paymentMethod.joinToString()}")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paymentMethod)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}