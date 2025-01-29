package com.example.finalsubmissionbasicandroiddicoding

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TicketDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_detail)

        supportActionBar?.apply {
            title = getString(R.string.airlines_detail)
        }

        val ticketDetail = intent.getParcelableExtra<Airlines>(MainActivity.INTENT_PARCELABLE)

        val photo = findViewById<ImageView>(R.id.photo_img)
        val name = findViewById<TextView>(R.id.ticket_title)
        val book = findViewById<TextView>(R.id.book_date)
        val detail = findViewById<TextView>(R.id.description)

        photo.setImageResource(ticketDetail?.photo!!)
        name.text = ticketDetail.name
        book.text= ticketDetail.book
        detail.text= ticketDetail.detail
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }
}