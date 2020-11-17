package com.cruckman900.mymarvelcomiccollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.cruckman900.mymarvelcomiccollection.helpers.Helper
import com.cruckman900.mymarvelcomiccollection.model.*
import com.cruckman900.mymarvelcomiccollection.view.CollectionFragment
import com.cruckman900.mymarvelcomiccollection.view.NewReleasesFragment

// https://gateway.marvel.com:443
// Public Marvel API Key: f83c91588c917f5891341f1bd722d5ac
// Private Marvel API Key: 10fc648aad5c31eb0dac7df4d43d8ed77331c30c

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

                Toast.makeText(this, "New Releases", Toast.LENGTH_LONG).show()
            }
            R.id.search -> {}
            R.id.search_by_photo -> {}
            R.id.my_collection -> {
                val fragment = CollectionFragment.createCollectionFragment()

                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()

                Toast.makeText(this, "My Collection", Toast.LENGTH_LONG).show()
            }
            R.id.wish_list -> {}
            R.id.clear_all_tables -> {
                var db = MMCCDB.createInstance(this)
                val thread = Thread() {
                    db.collectionDao().nukeCollection()
                }
                thread.start()

                val fragment = NewReleasesFragment.createNewReleases()

                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            }
            else -> Toast.makeText(this, "Invalid Menu Selection", Toast.LENGTH_LONG).show()
        }
        return true
    }

    fun addToCollection(data: Results) {
        val thread = Thread {
            var collectionEntity = CollectionEntity()
            collectionEntity.title = data.title.split(" (")[0]
            collectionEntity.year = data.dates[0].date.split("-")[0].toInt()
            collectionEntity.issueNumber = data.issueNumber.toInt()
            collectionEntity.imageURL = "${data.thumbnail.path}.${data.thumbnail.extension}"

            var db = MMCCDB.createInstance(this)

            db.collectionDao().saveToCollection(collectionEntity)

            for (entity in db.collectionDao().getCollection()) {
                Log.i(TAG, "addToCollection: Title: ${entity.title}")
            }
        }
        thread.start()
        Toast.makeText(this, "Add To Collection: ${data.title.split(" (")[0]}", Toast.LENGTH_LONG).show()
    }

    fun addToWishlist(data: Results) {
        val thread = Thread {
            var wishlistEntity = WishlistEntity()
            wishlistEntity.title = data.title.split(" (")[0]
            wishlistEntity.year = data.dates[0].date.split("-")[0].toInt()
            wishlistEntity.issueNumber = data.issueNumber.toInt()
            wishlistEntity.imageURL = "${data.thumbnail.path}.${data.thumbnail.extension}"

            var db = MMCCDB.createInstance(this)

            db.wishlistDao().saveToWishlist(wishlistEntity)

            for (entity in db.wishlistDao().getWishlist()) {
                Log.i(TAG, "addToWishlist: Title: ${entity.title}")
            }
        }
        thread.start()
        Toast.makeText(this, "Add To Wishlist: ${data.title.split(" (")[0]}", Toast.LENGTH_LONG).show()
    }
}