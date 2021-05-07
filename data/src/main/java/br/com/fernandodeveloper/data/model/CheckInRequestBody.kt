package br.com.fernandodeveloper.data.model

data class CheckInRequestBody(
    val email: String,
    val eventId: String,
    val name: String
)
