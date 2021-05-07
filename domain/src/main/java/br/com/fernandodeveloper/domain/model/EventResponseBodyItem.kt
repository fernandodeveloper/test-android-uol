package br.com.fernandodeveloper.domain.model

data class EventResponseBodyItem(
    val id: String,
    val title: String,
    val description: String,
    val date: Int,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val people: List<Any>,
    val price: Double
)
