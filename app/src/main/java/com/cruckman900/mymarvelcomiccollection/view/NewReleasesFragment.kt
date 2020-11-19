package com.cruckman900.mymarvelcomiccollection.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cruckman900.mymarvelcomiccollection.MMCCApplication
import com.cruckman900.mymarvelcomiccollection.MainActivity
import com.cruckman900.mymarvelcomiccollection.R
import com.cruckman900.mymarvelcomiccollection.helpers.Helper
import com.cruckman900.mymarvelcomiccollection.model.ComicResponse
import com.cruckman900.mymarvelcomiccollection.model.Results
import com.cruckman900.mymarvelcomiccollection.viewmodel.AppState
import com.cruckman900.mymarvelcomiccollection.viewmodel.ComicAdapter
import com.cruckman900.mymarvelcomiccollection.viewmodel.MMCCViewModel
import com.cruckman900.mymarvelcomiccollection.viewmodel.MMCCViewModelProvider
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*
import java.lang.Exception
import javax.inject.Inject

private const val TAG = "NewReleasesFragment"

class NewReleasesFragment: Fragment() {
    lateinit var collectionListener: (data: Results) -> Unit
    lateinit var wishlistListener: (data: Results) -> Unit
    override fun onAttach(context: Context) {
        super.onAttach(context)
        collectionListener = (context as MainActivity)::addToCollection
        wishlistListener = (context as MainActivity)::addToWishlist
    }

    companion object {
        fun createNewReleases() : NewReleasesFragment {
            return NewReleasesFragment()
        }
    }

    private lateinit var mmccViewModel: MMCCViewModel
    @Inject
    lateinit var mmccProvider : MMCCViewModelProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MMCCApplication.getComponent().injectNewReleases(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_recyclerview, container, false)

        mmccViewModel = mmccProvider.create(MMCCViewModel::class.java)

        try {
            mmccViewModel.getMutableLiveData().observe(this, object : Observer<AppState> {
                override fun onChanged(t: AppState?) {
                    Log.d(TAG, "onChanged: hmmm3 ${t.toString()}")
                    t?.let {
                        when (t) {
                            is AppState.COMICRESPONSE -> {
                                Log.d(TAG, "onChanged: comicresponse")
                                val attributionText = t.comicResponse.attributionText
                                Helper.attributionText = attributionText

                                val data = t.comicResponse.data
                                val results = data.results

                                inflateRecyclerView(results, view)
                            }
                            else -> {
                                Log.d(TAG, "onChanged: not comicresponse")
                                Toast.makeText(activity, t.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            })
        } catch (e: Exception) {
            Log.e(TAG, "onCreateView: ${e.message}")
        }

        Log.d(TAG, "onCreateView: hmmm ${Helper.getDateRangeString()}")
        mmccViewModel.getNewReleases(Helper.getDateRangeString())
        return view
    }

    private fun inflateRecyclerView(dataSet: List<Results>, view: View) {
        Log.d(TAG, "inflateRecyclerView: am I getting here? dataSet")
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        view.recyclerview.layoutManager = linearLayoutManager
        view.recyclerview.adapter = ComicAdapter(dataSet, collectionListener, wishlistListener)
    }
}