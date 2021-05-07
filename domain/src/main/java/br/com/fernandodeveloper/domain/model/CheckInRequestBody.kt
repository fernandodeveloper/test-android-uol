package br.com.fernandodeveloper.domain.model

data class CheckInRequestBody(
    val eventId: String,
    val email: String,
    val name: String
)
