package com.example.cool_beers.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.cool_beers.data.remote.BeerApi
import com.example.cool_beers.data.repository.Repository
import com.example.cool_beers.domain.Domain
import com.example.cool_beers.presentation.viewmodel.BeerViewModel
import kotlinx.coroutines.Dispatchers

object BeerDI {
    fun provideViewModel(viewModelStoreOwner: ViewModelStoreOwner): BeerViewModel{
        return beerProvider(viewModelStoreOwner)[BeerViewModel::class.java]
    }

    private fun beerProvider(viewModelStoreOwner: ViewModelStoreOwner): ViewModelProvider{
        return ViewModelProvider(viewModelStoreOwner,
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return BeerViewModel(provideDomain(), provideDefaultDispatcher()) as T
                }
            })
    }

    private fun provideDomain(): Domain{
        return Repository(
            BeerApi.initRetrofit()
        )
    }

    fun provideDefaultDispatcher() = Dispatchers.Default
}