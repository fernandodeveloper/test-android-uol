package br.com.fernandodeveloper.data.mapper


fun br.com.fernandodeveloper.domain.model.CheckInRequestBody.toDataCheckInRequestBody() =
    br.com.fernandodeveloper.data.model.CheckInRequestBody(
        email = email,
        eventId = eventId,
        name = name
    )