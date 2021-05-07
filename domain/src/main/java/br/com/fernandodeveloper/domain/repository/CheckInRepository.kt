package br.com.fernandodeveloper.domain.repository

import br.com.fernandodeveloper.domain.utils.Either
import br.com.fernandodeveloper.domain.model.CheckInRequestBody


interface CheckInRepository {

    suspend fun makeCheckIn(checkInRequestBody: CheckInRequestBody): Either<Unit, Exception>
}

