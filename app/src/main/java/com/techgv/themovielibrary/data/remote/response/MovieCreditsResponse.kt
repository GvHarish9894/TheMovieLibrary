package com.techgv.themovielibrary.data.remote.response

data class MovieCreditsResponse(
    val cast: List<Movies>,
    val crew: List<Movies>,
    val id: Int
)