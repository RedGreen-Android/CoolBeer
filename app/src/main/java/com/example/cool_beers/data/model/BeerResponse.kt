package com.example.cool_beers.data.model

data class BeerResponse(
    val id: Int,
    val name: String,
    val tagline: String,
    val first_brewed: String,
    val description: String,
    val image_url: String,
    val ibu: Float?,
    val ingredients: BeerIngredient,
    val food_pairing: List<String>
)

data class BeerIngredient(
    val malt: List<Description>,
    val hops: List<Description>,
    val yeast: String
)

data class Description(
    val name: String,
    val amount: BeerAmount
)

data class BeerAmount(
    val value: Float,
    val unit: String
)