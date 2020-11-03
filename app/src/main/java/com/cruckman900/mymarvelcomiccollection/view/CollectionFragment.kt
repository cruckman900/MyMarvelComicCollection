package com.cruckman900.mymarvelcomiccollection.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cruckman900.mymarvelcomiccollection.MMCCApplication
import com.cruckman900.mymarvelcomiccollection.R
import com.cruckman900.mymarvelcomiccollection.viewmodel.MMCCViewModel
import com.cruckman900.mymarvelcomiccollection.viewmodel.MMCCViewModelProvider
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*
import javax.inject.Inject

class CollectionFragment: Fragment() {
    companion object {
        fun createCollectionFragment(): CollectionFragment {
            return CollectionFragment()
        }
    }

    private lateinit var mmccViewModel: MMCCViewModel
    @Inject
    lateinit var mmccProvider: MMCCViewModelProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MMCCApplication.getComponent().injectCollection(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_recyclerview, container, false)

        mmccViewModel = mmccProvider.create(MMCCViewModel::class.java)

        return view
    }
}