package com.cruckman900.mymarvelcomiccollection.model

import com.squareup.moshi.Json

data class ComicResponse (
    @Json(name = "attributionText")
    val attributionText: String,
    @Json(name = "data")
    val data: Data
)

data class Data (
    @Json(name = "results")
    val results: List<Results>
)

data class Results (
    @Json(name = "title")
    val title: String,
    @Json(name = "issueNumber")
    val issueNumber: Double,
    @Json(name = "description")
    val description: String,
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail
)

data class Thumbnail (
    @Json(name = "path")
    val path: String,
    @Json(name = "extension")
    val extension: String
)