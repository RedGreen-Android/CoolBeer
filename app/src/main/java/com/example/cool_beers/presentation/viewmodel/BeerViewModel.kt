package com.example.cool_beers.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.cool_beers.domain.Domain
import com.example.cool_beers.domain.PresentationData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "BeerViewModel"
class BeerViewModel(private val domain: Domain,
                    private val defaultDispatcher: CoroutineDispatcher): ViewModel() {

    private val _beers = MutableStateFlow<PresentationData>(PresentationData.Loading())
    val beers: StateFlow<PresentationData>
        get() = _beers

    init {
        CoroutineScope(defaultDispatcher).launch {
            domain.useCaseGetAllBeers().collect {
                Log.d(TAG, "collectData: $it")
                _beers.value = it
            }
        }
    }

    fun getBeerById(beerId: Int){
        CoroutineScope(defaultDispatcher).launch {
            domain.useCaseGetOneBeer(beerId).collect {
                _beers.value = it
            }
        }
    }
}