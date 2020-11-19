package com.cruckman900.mymarvelcomiccollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cruckman900.mymarvelcomiccollection.helpers.Helper
import com.cruckman900.mymarvelcomiccollection.model.*
import com.cruckman900.mymarvelcomiccollection.view.CollectionFragment
import com.cruckman900.mymarvelcomiccollection.view.NewReleasesFragment
import com.cruckman900.mymarvelcomiccollection.view.WishlistFragment

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
        lateinit var fragment: Fragment
        lateinit var message: String
        when (item.itemId) {
            R.id.new_releases -> {
                fragment = NewReleasesFragment.createNewReleases()

                message = "New Releases"
            }
            R.id.search -> {}
            R.id.search_by_photo -> {}
            R.id.my_collection -> {
                fragment = CollectionFragment.createCollectionFragment()

                message = "My Collection"
            }
            R.id.wish_list -> {
                fragment = WishlistFragment.createWishlistFragment()

                message = "My Wishlist"
            }
            R.id.clear_all_tables -> {
                var db = MMCCDB.createInstance(this)
                val thread = Thread() {
                    db.collectionDao().nukeCollection()
                    db.wishlistDao().nukeWishlist()
                }
                thread.start()

                fragment = NewReleasesFragment.createNewReleases()

                message = "Database Wiped"
            }
            else -> message = "Invalid Menu Selection"
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()

        Toast.makeText(this, "$message", Toast.LENGTH_LONG).show()

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
        }
        thread.start()
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
       }
        thread.start()
    }
}