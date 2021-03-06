package com.cruckman900.mymarvelcomiccollection.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cruckman900.mymarvelcomiccollection.MMCCApplication
import com.cruckman900.mymarvelcomiccollection.R
import com.cruckman900.mymarvelcomiccollection.model.WishlistEntity
import com.cruckman900.mymarvelcomiccollection.viewmodel.AppState
import com.cruckman900.mymarvelcomiccollection.viewmodel.MMCCViewModel
import com.cruckman900.mymarvelcomiccollection.viewmodel.MMCCViewModelProvider
import com.cruckman900.mymarvelcomiccollection.viewmodel.WishlistAdapter
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*
import javax.inject.Inject

private const val TAG = "WishlistFragment"

class WishlistFragment: Fragment() {
    companion object {
        fun createWishlistFragment(): WishlistFragment {
            return WishlistFragment()
        }
    }

    private lateinit var mmccViewModel: MMCCViewModel
    @Inject
    lateinit var  mmccProvider: MMCCViewModelProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MMCCApplication.getComponent().injectWishlist(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_recyclerview, container, false)

        mmccViewModel = mmccProvider.create(MMCCViewModel::class.java)

        try {
            mmccViewModel.getMutableLiveData().observe(this, object : Observer<AppState> {
                override fun onChanged(t: AppState?) {
                    t?.let {
                        when (t) {
                            is AppState.WISHLISTRESPONSE -> {
                                val data = t.wishlistResponse

                                inflateRecyclerView(data, view)
                            }
                            else -> {
                                Toast.makeText(activity, t.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

            })
        } catch (e: Exception) {
            Log.e(TAG, "onCreateView: ${e.message}")
        }

        mmccViewModel.getWishlist(context!!)
        return view
    }

    private fun inflateRecyclerView(dataSet: List<WishlistEntity>, view: View) {
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        view.recyclerview.layoutManager = linearLayoutManager
        view.recyclerview.adapter = WishlistAdapter(dataSet)
    }
}