package com.example.cool_beers.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Text(text = beer.name,fontWeight = FontWeight.Bold, color  = Color.DarkGray, fontSize = 17.sp )
            Text(text = beer.tagline)
            Text(text = beer.first_brewed)
            Text(text = beer.ibu.toString())
            Text(text = beer.description + "\n")
            Text(text = "Ingredients: " , fontWeight = FontWeight.Bold )
            Text(text = beer.ingredients.yeast + " + " )
            for (i in beer.ingredients.malt ) {
                Text(text = i.name)
            }
            for (i in beer.ingredients.hops ) {
                Text(text = i.name)
                }
            Text(text = "\nFood it goes well with: " , fontWeight = FontWeight.Bold )
            for (i in beer.food_pairing) {
                Text(text = i)
            }
        }
    }
}

