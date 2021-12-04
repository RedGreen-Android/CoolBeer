package com.example.cool_beers.domain

import com.example.cool_beers.data.model.BeerResponse

sealed class PresentationData{
    data class Response(val data : List<BeerResponse>): PresentationData()
    data class Error(val errorMessage: String): PresentationData()
    data class Loading(val isLoading: Boolean = true): PresentationData()
}