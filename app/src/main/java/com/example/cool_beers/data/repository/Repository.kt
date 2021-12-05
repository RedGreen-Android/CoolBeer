package com.example.cool_beers.data.repository

import com.example.cool_beers.data.Constants
import com.example.cool_beers.data.remote.BeerApi
import com.example.cool_beers.domain.Domain
import com.example.cool_beers.domain.PresentationData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(private val service: BeerApi): Domain {

    override fun useCaseGetAllBeers(): Flow<PresentationData> = flow {
        emit(PresentationData.Loading())

        val response = service.getAllBeers()

        if (response != null && response.isNotEmpty())
            emit(PresentationData.Response(response))
        else
            emit(PresentationData.Error(Constants.ServerError))
    }

    override fun useCaseGetOneBeer(id: Int): Flow<PresentationData> = flow {
        emit(PresentationData.Loading())

        val response = service.getBeerId(id)

        if (response != null)
            emit(PresentationData.Response(response))
        else
            emit(PresentationData.Error(Constants.ServerError))
    }
}