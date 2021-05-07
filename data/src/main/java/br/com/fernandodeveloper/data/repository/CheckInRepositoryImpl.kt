package br.com.fernandodeveloper.data.repository

import br.com.fernandodeveloper.data.mapper.toDataCheckInRequestBody
import br.com.fernandodeveloper.domain.exceptions.GenericException
import br.com.fernandodeveloper.domain.exceptions.NoNetworkingException
import br.com.fernandodeveloper.domain.model.CheckInRequestBody
import br.com.fernandodeveloper.domain.repository.CheckInRepository
import br.com.fernandodeveloper.domain.utils.Either
import br.com.fernandodeveloper.data.api.CheckInAPI

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class CheckInRepositoryImpl(
    private val checkInAPI: CheckInAPI
) : CheckInRepository {

    override suspend fun makeCheckIn(checkInRequestBody: CheckInRequestBody): Either<Unit, Exception> =
        withContext(Dispatchers.IO) {
            try {
                val response = checkInAPI.makeCheckIn(
                    requestBody = checkInRequestBody.toDataCheckInRequestBody()
                )
                when (response.code()) {
                    201 -> Either.Success(Unit)
                    else -> Either.Failure(GenericException)
                }
            } catch (e: UnknownHostException) {
                Either.Failure(NoNetworkingException)
            }
        }
}
