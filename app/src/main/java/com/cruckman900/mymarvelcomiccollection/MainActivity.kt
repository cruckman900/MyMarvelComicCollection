package com.cruckman900.mymarvelcomiccollection

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.cruckman900.mymarvelcomiccollection.helpers.Helper
import com.cruckman900.mymarvelcomiccollection.view.NewReleasesFragment

// https://gateway.marvel.com:443
// Public Marvel API Key: f83c91588c917f5891341f1bd722d5ac
// Private Marvel API Key: 10fc648aad5c31eb0dac7df4d43d8ed77331c30c

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var db = Room.databaseBuilder(
//            applicationContext,
//            MMCCDB::class.java,
//            "mmcc_db"
//        ).build()
        Log.d(TAG, "onCreate: ${Helper.getDateRangeString()}")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_releases -> {
                val fragment = NewReleasesFragment.createNewReleases()

                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            }
            R.id.search -> {}
            R.id.search_by_photo -> {}
            R.id.my_collection -> {}
            R.id.wish_list -> {}
            else -> Toast.makeText(this, "Invalid Menu Selection", Toast.LENGTH_LONG).show()
        }
        return true
    }
}