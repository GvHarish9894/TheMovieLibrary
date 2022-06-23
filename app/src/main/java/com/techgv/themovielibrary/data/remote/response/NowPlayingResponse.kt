package com.techgv.themovielibrary.data.remote.response

data class NowPlayingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movies>,
    val total_pages: Int,
    val total_results: Int
)