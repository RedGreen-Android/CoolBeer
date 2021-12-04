package com.example.cool_beers.domain

import kotlinx.coroutines.flow.Flow

interface Domain {
    fun useCaseGetAllBeers(): Flow<PresentationData>
    fun useCaseGetOneBeer(id: Int): Flow<PresentationData>
}