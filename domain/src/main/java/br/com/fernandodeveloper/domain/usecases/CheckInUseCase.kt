package br.com.fernandodeveloper.domain.usecases

import br.com.fernandodeveloper.domain.exceptions.CheckinException
import br.com.fernandodeveloper.domain.model.CheckInRequestBody
import br.com.fernandodeveloper.domain.repository.CheckInRepository
import br.com.fernandodeveloper.domain.utils.Either
import br.com.fernandodeveloper.domain.utils.EmailValidator

class CheckInUseCase(
    private val checkInRepository: CheckInRepository
) {

    suspend fun execute(checkInRequestBody: CheckInRequestBody?): Either<Unit, Exception> {
        if (checkInRequestBody == null) throw IllegalArgumentException()
        return when {
            checkInRequestBody.name.isEmpty() -> Either.Failure(CheckinException.EmptyNameException)
            checkInRequestBody.email.isEmpty() -> Either.Failure(CheckinException.EmptyEmailException)
            !EmailValidator.isEmailValid(checkInRequestBody.email) -> Either.Failure(CheckinException.InvalidEmailException)
            else -> checkInRepository.makeCheckIn(checkInRequestBody)
        }
    }
}
