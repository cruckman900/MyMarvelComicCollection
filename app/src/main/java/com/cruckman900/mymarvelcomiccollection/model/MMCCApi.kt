package com.cruckman900.mymarvelcomiccollection.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.cruckman900.mymarvelcomiccollection.helpers.Helper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MMCCApi {
    @GET("v1/public/comics")
    fun getNewReleases(
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("dateRange") dateRange: String,
        @Query("limit") limit: Int = 99,
        @Query("noVariants") noVariants: Boolean = true,
        @Query("formatType") formatType: String = "comic",
        @Query("apikey") apiKey: String = "f83c91588c917f5891341f1bd722d5ac",
        @Query("orderBy") orderBy: String ="title,onsaleDate,issueNumber"
    ) : Call<ComicResponse>

    @GET("v1/public/comics")
    fun getComicByCoverDetail(
        @Query("title") title: String,
        @Query("startYear") startYear: Int,
        @Query("issueNumber") issueNumber: Int,
        @Query("noVariants") noVariants: Boolean = true,
        @Query("apikey") apiKey: String = "f83c91588c917f5891341f1bd722d5ac"
    ) : Call<ComicResponse>

    @GET("v1/public/comics")
    fun getComicByUPC(
        @Query("upc") upc: String,
        @Query("noVariants") noVariants: Boolean = true,
        @Query("apikey") apiKey: String = "f83c91588c917f5891341f1bd722d5ac"
    ) : Call<ComicResponse>

    @GET("v1/public/comics")
    fun getComicByISBN(
        @Query("isbn") isbn: String,
        @Query("noVariants") noVariants: Boolean = true,
        @Query("apikey") apiKey: String = "f83c91588c917f5891341f1bd722d5ac"
    ) : Call<ComicResponse>

    @GET("v2/public/comics")
    fun getComicByEAN(
        @Query("ean") ean: String,
        @Query("noVariants") noVariants: Boolean = true,
        @Query("apikey") apiKey: String = "f83c91588c917f5891341f1bd722d5ac"
    ) : Call<ComicResponse>

    @GET("v1/public/comics")
    fun getComicByISSN(
        @Query("issn") issn: String,
        @Query("noVariants") noVariants: Boolean = true,
        @Query("apikey") apiKey: String = "f83c91588c917f5891341f1bd722d5ac"
    ) : Call<ComicResponse>
}