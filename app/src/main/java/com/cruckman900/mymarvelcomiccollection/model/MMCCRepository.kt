package com.cruckman900.mymarvelcomiccollection.model

import android.content.Context
import android.util.Log
import com.cruckman900.mymarvelcomiccollection.MainActivity
import com.cruckman900.mymarvelcomiccollection.helpers.Helper
import com.cruckman900.mymarvelcomiccollection.viewmodel.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MMCCRepository"

class MMCCRepository(val api: MMCCApi) {
    fun repoGetNewReleases(
            callback: (data: AppState) -> Unit,
            dateRange: String) {
        val ts = Helper.getTimeStamp()
        val hash = Helper.buildHash(
                ts,
                "10fc648aad5c31eb0dac7df4d43d8ed77331c30c",
                "f83c91588c917f5891341f1bd722d5ac",
                "md5"
        )
        api.getNewReleases(hash, ts, dateRange)
                .enqueue(object : Callback<ComicResponse> {
                    override fun onResponse(
                            call: Call<ComicResponse>,
                            response: Response<ComicResponse>
                    ) {
                        Log.d(TAG, "onResponse: hmmm")
                        if (response.isSuccessful) {
                            Log.d(TAG, "onResponse: isSuccessful")
                            response.body()?.let {
                                val appState = AppState.COMICRESPONSE(it)
                                callback.invoke(appState)
                            }
                        } else {
                            Log.d(TAG, "onResponse: not successful")
                        }
                    }

                    override fun onFailure(call: Call<ComicResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message}")
                        val appState = AppState.ERROR(t.message ?: "Something Failed")
                        callback.invoke(appState)
                    }

                })
    }

    fun repoComicByCoverDetail(
            callback: (data: AppState) -> Unit,
            title: String,
            startYear: Int,
            issueNumber: Int) {
        api.getComicByCoverDetail(title, startYear, issueNumber)
                .enqueue(object : Callback<ComicResponse> {
                    override fun onResponse(
                        call: Call<ComicResponse>,
                        response: Response<ComicResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                val appState = AppState.COMICRESPONSE(it)
                                callback.invoke(appState)
                            }
                        }
                    }

                    override fun onFailure(call: Call<ComicResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message}")
                        val appState = AppState.ERROR(t.message ?: "Something Failed")
                        callback.invoke(appState)
                    }

                })
    }

    fun repoComicByUPC(
            callback: (data: AppState) -> Unit,
            upc: String) {
        api.getComicByUPC(upc)
                .enqueue(object : Callback<ComicResponse> {
                    override fun onResponse(
                            call: Call<ComicResponse>,
                            response: Response<ComicResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                val appState = AppState.COMICRESPONSE(it)
                                callback.invoke(appState)
                            }
                        }
                    }

                    override fun onFailure(call: Call<ComicResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message}")
                        val appState = AppState.ERROR(t.message ?: "Something Failed")
                        callback.invoke(appState)
                    }

                })
    }

    fun repoComicByISBN(
            callback: (data: AppState) -> Unit,
            isbn: String) {
        api.getComicByUPC(isbn)
                .enqueue(object : Callback<ComicResponse> {
                    override fun onResponse(
                            call: Call<ComicResponse>,
                            response: Response<ComicResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                val appState = AppState.COMICRESPONSE(it)
                                callback.invoke(appState)
                            }
                        }
                    }

                    override fun onFailure(call: Call<ComicResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message}")
                        val appState = AppState.ERROR(t.message ?: "Something Failed")
                        callback.invoke(appState)
                    }

                })
    }

    fun repoComicByEAN(
            callback: (data: AppState) -> Unit,
            ean: String) {
        api.getComicByUPC(ean)
                .enqueue(object : Callback<ComicResponse> {
                    override fun onResponse(
                            call: Call<ComicResponse>,
                            response: Response<ComicResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                val appState = AppState.COMICRESPONSE(it)
                                callback.invoke(appState)
                            }
                        }
                    }

                    override fun onFailure(call: Call<ComicResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message}")
                        val appState = AppState.ERROR(t.message ?: "Something Failed")
                        callback.invoke(appState)
                    }

                })
    }

    fun repoComicByISSN(
            callback: (data: AppState) -> Unit,
            issn: String) {
        api.getComicByUPC(issn)
                .enqueue(object : Callback<ComicResponse> {
                    override fun onResponse(
                            call: Call<ComicResponse>,
                            response: Response<ComicResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                val appState = AppState.COMICRESPONSE(it)
                                callback.invoke(appState)
                            }
                        }
                    }

                    override fun onFailure(call: Call<ComicResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message}")
                        val appState = AppState.ERROR(t.message ?: "Something Failed")
                        callback.invoke(appState)
                    }

                })
    }

    fun repoGetCollection(
        callback: (data: AppState) -> Unit,
        context: Context
    ) {
        val thread = Thread() {
            var db = MMCCDB.createInstance(context)

            val appState = AppState.COLLECTIONRESPONSE(db.collectionDao().getCollection())
            callback.invoke(appState)
        }
        thread.start()
    }
}