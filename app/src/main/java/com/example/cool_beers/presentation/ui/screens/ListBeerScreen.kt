package com.example.cool_beers.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cool_beers.common.Router
import com.example.cool_beers.data.model.BeerResponse
import com.example.cool_beers.domain.PresentationData
import com.example.cool_beers.presentation.viewmodel.BeerViewModel

private const val TAG = "ListBeerScreen"

@Composable
fun ListBeerScreen(viewModel: BeerViewModel, navController: NavController) {
    val state = viewModel.beers.collectAsState().value
    Log.d(TAG, "ListBeerScreen: $state")
    when (state){
        is PresentationData.Response-> {
            ListBeer(state.data){ beerId->
                navController.navigate(Router.Details(beerId).toString())
            }
        }
        is PresentationData.Error-> {

        }
        is PresentationData.Loading-> {

        }
    }
}

@Composable
fun ListBeer(dataSet: List<BeerResponse>, openBeer: (Int) -> Unit) {
    LazyColumn {
        items(dataSet) {
            Card(Modifier.clickable { openBeer(it.id) }.padding(4.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    BeerItem(beerData = it.name)
                    BeerItem(beerData = it.ibu.toString())
                    BeerItem(beerData = it.tagline)
                }
            }
        }
    }
}

@Composable
fun BeerItem(beerData: String) {
    // name, ibu, tagline
    Text(text = beerData, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(2.dp))
}