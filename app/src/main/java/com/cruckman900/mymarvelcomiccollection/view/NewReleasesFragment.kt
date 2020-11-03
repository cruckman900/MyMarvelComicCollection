package com.cruckman900.mymarvelcomiccollection.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cruckman900.mymarvelcomiccollection.MMCCApplication
import com.cruckman900.mymarvelcomiccollection.R
import com.cruckman900.mymarvelcomiccollection.helpers.Helper
import com.cruckman900.mymarvelcomiccollection.model.ComicResponse
import com.cruckman900.mymarvelcomiccollection.model.Results
import com.cruckman900.mymarvelcomiccollection.viewmodel.AppState
import com.cruckman900.mymarvelcomiccollection.viewmodel.ComicAdapter
import com.cruckman900.mymarvelcomiccollection.viewmodel.MMCCViewModel
import com.cruckman900.mymarvelcomiccollection.viewmodel.MMCCViewModelProvider
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*
import javax.inject.Inject

private const val TAG = "NewReleasesFragment"

class NewReleasesFragment: Fragment() {
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
                        }
                    }
                }
            }

        })
        Log.d(TAG, "onCreateView: hmmm")
        mmccViewModel.getNewReleases(Helper.getDateRangeString())
        Log.d(TAG, "onCreateView: hmmm2")
        return view
    }

    private fun inflateRecyclerView(dataSet: List<Results>, view: View) {
        Log.d(TAG, "inflateRecyclerView: am I getting here?")
        val linearLayoutManager = LinearLayoutManager(activity)

        view.recyclerview.layoutManager = linearLayoutManager
        view.recyclerview.adapter = ComicAdapter(dataSet)
    }
}