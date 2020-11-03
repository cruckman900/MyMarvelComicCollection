package com.cruckman900.mymarvelcomiccollection.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cruckman900.mymarvelcomiccollection.model.ComicResponse
import com.cruckman900.mymarvelcomiccollection.model.MMCCRepository

sealed class AppState {
    data class ERROR(val errorMessage: String) : AppState()
    data class COMICRESPONSE(val comicResponse: ComicResponse) : AppState()
}

private const val TAG = "MMCCViewModel"

class MMCCViewModel(val repo: MMCCRepository) : ViewModel() {
    private val mmccMutableLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getMutableLiveData() : LiveData<AppState> {
        return mmccMutableLiveData
    }

    fun getNewReleases(dateRange: String) {
        repo.repoGetNewReleases(::callbackData, dateRange)
        Log.d(TAG, "getNewReleases: $dateRange")
    }

    fun getComicByCoverDetail(title: String, startYear: Int, issueNumber: Int) {
        repo.repoComicByCoverDetail(::callbackData, title, startYear, issueNumber)
    }

    fun getComicByUPC(upc: String) {
        repo.repoComicByUPC(::callbackData, upc)
    }

    fun getComicByISBN(isbn: String) {
        repo.repoComicByUPC(::callbackData, isbn)
    }

    fun getComicByEAN(ean: String) {
        repo.repoComicByUPC(::callbackData, ean)
    }

    fun getComicByISSN(issn: String) {
        repo.repoComicByUPC(::callbackData, issn)
    }

    fun callbackData(data: AppState) {
        Log.d(TAG, "callbackData: $data")
        mmccMutableLiveData.value = data
    }
}