package com.example.cool_beers.common

sealed class Router {
    class Details(private val beerId: Int = 0){
        val detailsRouter = "Details/{beerId}"
        override fun toString(): String {
            return "Details/$beerId"
        }
    }
    object List: Router()
}