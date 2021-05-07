package br.com.fernandodeveloper.domain.tests

import assertk.assertions.isEqualTo
import br.com.fernandodeveloper.domain.exceptions.CheckinException
import br.com.fernandodeveloper.domain.model.CheckInRequestBody
import br.com.fernandodeveloper.domain.repository.CheckInRepository
import br.com.fernandodeveloper.domain.usecases.CheckInUseCase
import br.com.fernandodeveloper.domain.utils.Either
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CheckInUseCaseTest {

    private val checkInRepository = mockk<CheckInRepository>()
    private val context = CheckInUseCase(checkInRepository)

    companion object {
        private const val EMPTY_FIELD = ""
        private const val VALID_EMAIL = "testando@emailvalido.com"
        private const val INVALID_EMAIL = "noemailValid.Fernando"
        private const val VALID_ID = "100"
        private const val VALID_NAME = "my name is test"
        private val checkInRequestModelWithEmptyEmail =
            CheckInRequestBody(
                email = EMPTY_FIELD,
                eventId = VALID_ID,
                name = VALID_NAME
            )
        private val checkInRequestModelWithInvalidEmail =
            CheckInRequestBody(
                email = INVALID_EMAIL,
                eventId = VALID_ID,
                name = VALID_NAME
            )
        private val checkInRequestModelWithIEmptyName =
            CheckInRequestBody(
                email = VALID_EMAIL,
                eventId = VALID_ID,
                name = EMPTY_FIELD
            )
        private val validCheckInRequestModel =
            CheckInRequestBody(
                email = VALID_EMAIL,
                eventId = VALID_ID,
                name = VALID_NAME
            )
    }

    @Test
    fun `check if when checkInRequestBody has empty email returns EmptyEmailException `() =
        runBlocking {
            //when
            val result = context.execute(
                checkInRequestBody = checkInRequestModelWithEmptyEmail
            )

            //then
            assertk.assertThat(result)
                .isEqualTo(Either.Failure(CheckinException.EmptyEmailException))
            coVerify(exactly = 0) {
                checkInRepository.makeCheckIn(
                    checkInRequestBody = checkInRequestModelWithEmptyEmail
                )
            }
        }

    @Test
    fun `check if when checkInRequestBody has invalid email returns InvalidEmailException `() =
        runBlocking {
            //when
            val result = context.execute(
                checkInRequestBody = checkInRequestModelWithInvalidEmail
            )

            //then
            assertk.assertThat(result)
                .isEqualTo(Either.Failure(CheckinException.InvalidEmailException))
            coVerify(exactly = 0) {
                checkInRepository.makeCheckIn(
                    checkInRequestBody = checkInRequestModelWithInvalidEmail
                )
            }
        }

    @Test
    fun `check if when checkInRequestBody has empty name returns EmptyNameException `() =
        runBlocking {
            //when
            val result = context.execute(
                checkInRequestBody = checkInRequestModelWithIEmptyName
            )

            //then
            assertk.assertThat(result)
                .isEqualTo(Either.Failure(CheckinException.EmptyNameException))
            coVerify(exactly = 0) {
                checkInRepository.makeCheckIn(
                    checkInRequestBody = checkInRequestModelWithIEmptyName
                )
            }
        }

    @Test
    fun `check if when everything is correct returns Unit `() =
        runBlocking {
            //given
            coEvery {
                checkInRepository.makeCheckIn(
                    checkInRequestBody = validCheckInRequestModel
                )
            } returns Either.Success(Unit)

            //when
            val result = context.execute(
                checkInRequestBody = validCheckInRequestModel
            )

            //then
            assertk.assertThat(result).isEqualTo(Either.Success(Unit))
            coVerify(exactly = 1) {
                checkInRepository.makeCheckIn(
                    checkInRequestBody = validCheckInRequestModel
                )
            }
        }


}
