package com.example.finalsubmissionbasicandroiddicoding

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAirlines: RecyclerView
    private val list = ArrayList<Airlines>()

    companion object {
        const val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvAirlines = findViewById(R.id.rv_pesawat)
        rvAirlines.setHasFixedSize(true)

        list.addAll(getListAirline())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvAirlines.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvAirlines.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, About::class.java)
                startActivity(moveIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListAirline(): ArrayList<Airlines> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataBook = resources.getStringArray(R.array.book_date)
        val dataDetail = resources.getStringArray(R.array.detail)
        val listAirline = ArrayList<Airlines>()
        for (position in dataName.indices) {
            val airline = Airlines(
                dataName[position],
                dataDescription[position],
                dataPhoto.getResourceId(position, -1),
                dataBook[position],
                dataDetail[position]
            )
            listAirline.add(airline)
        }
        return listAirline
    }

    private fun showRecyclerList() {
        rvAirlines.layoutManager = LinearLayoutManager(this)
        val listAirlineAdapter = ListAirlineAdapter(list)
        rvAirlines.adapter = listAirlineAdapter

        listAirlineAdapter.setOnItemClickCallback(object : ListAirlineAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Airlines) {
                showSelectedAirline(data)
            }
        })
    }

    private fun showSelectedAirline(airline: Airlines) {
        Toast.makeText(this, "Kamu memilih " + airline.name, Toast.LENGTH_SHORT).show()

        val intent = Intent(this, TicketDetail::class.java)
        intent.putExtra(INTENT_PARCELABLE, airline)
        startActivity(intent)
    }
}