package com.techgv.themovielibrary.data.remote.response

data class VideoResponse(
    val id: Int,
    val results: List<VideoResults>
)