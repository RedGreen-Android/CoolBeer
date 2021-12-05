package com.example.cool_beers.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.AnnotatedString
import com.example.cool_beers.common.allFood
import com.example.cool_beers.common.allIngredients
import com.example.cool_beers.data.model.BeerIngredient
import com.example.cool_beers.data.model.BeerResponse
import com.example.cool_beers.domain.PresentationData
import com.example.cool_beers.presentation.viewmodel.BeerViewModel

@Composable
fun DetailBeerScreen(viewModel: BeerViewModel, beerId: Int) {
    val state: PresentationData by viewModel.beers.collectAsState()

    viewModel.getBeerById(beerId)

    when (state) {
        is PresentationData.Response -> {
            DetailBeer((state as PresentationData.Response).data)
        }
        is PresentationData.Error -> {}
        is PresentationData.Loading -> {}
    }
}

@Composable
fun DetailBeer(data: List<BeerResponse>) {
    val data by remember {
        mutableStateOf<BeerResponse>(data[0])
    }
    Beer(beer = data)
}

@Composable
fun Beer(beer: BeerResponse) {
    Card {
        Column {
            Text(text = beer.name)
            Text(text = beer.tagline)
            Text(text = beer.first_brewed)
            Text(text = beer.ibu.toString())
            Text(text = beer.description)
            Text(text = beer.ingredients.yeast)
            Text(text = beer.food_pairing.allFood())

        }
    }
}

