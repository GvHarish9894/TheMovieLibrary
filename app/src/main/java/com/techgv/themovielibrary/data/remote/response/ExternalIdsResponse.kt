package com.techgv.themovielibrary.data.remote.response

data class ExternalIdsResponse(
    val facebook_id: String?,
    val freebase_id: String,
    val freebase_mid: String,
    val id: Int,
    val imdb_id: String,
    val instagram_id: String?,
    val tvrage_id: Int,
    val twitter_id: String?
)