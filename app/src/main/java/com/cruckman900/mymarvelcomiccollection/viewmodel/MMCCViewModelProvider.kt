package com.cruckman900.mymarvelcomiccollection.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cruckman900.mymarvelcomiccollection.model.MMCCRepository

class MMCCViewModelProvider(val repo: MMCCRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MMCCViewModel(repo) as T
    }
}