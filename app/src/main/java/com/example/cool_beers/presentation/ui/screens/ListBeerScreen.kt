package com.example.cool_beers.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(
            bottom = 6.dp, top = 6.dp, start = 4.dp, end = 4.dp
        ).fillMaxWidth(), elevation = 15.dp
    ) {
        LazyColumn {
            items(dataSet) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier= Modifier
                        .clickable { openBeer(it.id) }
                        .padding(5.dp)
                        .border(2.dp, Color.Cyan).wrapContentSize()
                    , elevation = 10.dp) {
                    Row(
                        modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(MaterialTheme.colors.surface)
                            .fillMaxWidth()
                            .background(brush = Brush.horizontalGradient(listOf(Color.Cyan, Color.White)))
                    ) {
                            BeerTitle(beerTitle = it.name)
                            BeerItem(beerData = it.ibu.toString())
                            BeerItem(beerData = it.tagline)
                    }
                }
            }
        }
    }
}

@Composable
fun BeerTitle(beerTitle: String){
    Text(text = beerTitle, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(5.dp), color = Color.DarkGray, fontSize = 17.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun BeerItem(beerData: String) {
    // name, ibu, tagline
    Text(text = beerData, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(5.dp), fontSize = 16.sp)
}

