package br.com.fernandodeveloper.data.api

import br.com.fernandodeveloper.data.model.CheckInRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckInAPI {

    @POST("checkin")
    suspend fun makeCheckIn(
        @Body requestBody: CheckInRequestBody
    ): Response<Void>
}