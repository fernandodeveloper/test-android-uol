package br.com.fernandodeveloper.data.api

import br.com.fernandodeveloper.data.model.ListEventsResponseBody
import br.com.fernandodeveloper.data.model.EventResponseBodyItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsAPI {

    @GET("events")
    suspend fun getListEvents(): Response<ListEventsResponseBody>

    @GET("events/{id}")
    suspend fun getEventById(
        @Path("id") id: String
    ): Response<EventResponseBodyItem>
}