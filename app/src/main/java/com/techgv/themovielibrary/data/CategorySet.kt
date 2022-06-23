package com.techgv.themovielibrary.data

import com.techgv.themovielibrary.data.remote.response.Movies

data class CategorySet(val title: Pair<String,String>, val item: List<Movies>)
