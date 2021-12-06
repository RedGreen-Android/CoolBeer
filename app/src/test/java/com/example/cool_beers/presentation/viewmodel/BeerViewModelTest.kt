package com.example.cool_beers.presentation.viewmodel

import com.example.cool_beers.data.Constants
import com.example.cool_beers.data.model.BeerAmount
import com.example.cool_beers.data.model.BeerIngredient
import com.example.cool_beers.data.model.BeerResponse
import com.example.cool_beers.data.model.Description
import com.example.cool_beers.domain.Domain
import com.example.cool_beers.domain.PresentationData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BeerViewModelTest{

    private lateinit var subject: BeerViewModel
    @Mock private lateinit var domain: Domain

    private val dataLoading = PresentationData.Loading()
    private val dataResponse = PresentationData.Response(listOf(BeerResponse(
        id = 2,
        name = "Trashy Blonde",
        tagline = "You Know You Shouldn't",
        first_brewed = 	"04/2008",
        description = 	"A titillating, neurotic, peroxide punk of a Pale Ale. Combining attitude, style, substance, and a little bit of low self esteem for good measure; what would your mother say? The seductive lure of the sassy passion fruit hop proves too much to resist. All that is even before we get onto the fact that there are no additives, preservatives, pasteurization or strings attached. All wrapped up with the customary BrewDog bite and imaginative twist.",
        image_url = 	"https://images.punkapi.com/v2/2.png",
        ibu = 	41.5F,
        ingredients = BeerIngredient(listOf(Description("", BeerAmount(0.0F, ""))),listOf(Description("",BeerAmount(0.0F, ""))),""),
        food_pairing = listOf("Fresh crab with lemon")
    )))
    private val dataError = PresentationData.Error(Constants.ServerError)

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        subject = BeerViewModel(domain, testDispatcher)
    }

    @Test
    fun `test happy path`(){
        assertNotNull(subject)

        assertNotNull(subject.beers.value)
        assertEquals(dataLoading, subject.beers.value)
    }
}