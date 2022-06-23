package com.techgv.themovielibrary.data.remote.response

data class CastResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)