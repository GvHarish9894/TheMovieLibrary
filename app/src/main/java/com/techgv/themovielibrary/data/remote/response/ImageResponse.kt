package com.techgv.themovielibrary.data.remote.response

data class ImageResponse(
    val backdrops: List<Backdrop>,
    val id: Int,
    val logos: List<Logo>,
    val posters: List<Poster>
)