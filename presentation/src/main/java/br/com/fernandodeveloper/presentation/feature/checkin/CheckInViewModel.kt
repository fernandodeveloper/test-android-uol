package br.com.fernandodeveloper.presentation.feature.checkin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fernandodeveloper.domain.exceptions.CheckinException
import br.com.fernandodeveloper.domain.exceptions.NoNetworkingException
import br.com.fernandodeveloper.domain.model.CheckInRequestBody
import br.com.fernandodeveloper.domain.usecases.CheckInUseCase
import br.com.fernandodeveloper.domain.utils.Either
import br.com.fernandodeveloper.presentation.R
import br.com.fernandodeveloper.presentation.StringLoader
import br.com.fernandodeveloper.presentation.br.com.fernandodeveloper.presentation.core.BaseViewModel

class CheckInViewModel(
    private val checkInUseCase: CheckInUseCase,
    private val stringLoader: StringLoader
) : BaseViewModel() {

    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    private val _nameError = MutableLiveData<String>()
    val nameError: LiveData<String> = _nameError

    private val _success = MutableLiveData<Unit>()
    val success: LiveData<Unit> = _success

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    private val _noNetworking = MutableLiveData<Unit>()
    val noNetworking: LiveData<Unit> = _noNetworking

    fun makeCheckIn(checkInRequestBody: CheckInRequestBody?) {
        launchSuspend {
            when (val result = checkInUseCase.execute(checkInRequestBody)) {
                is Either.Success -> _success.postValue(Unit)
                is Either.Failure -> when (result.cause) {
                    is CheckinException -> handlerFormException(result.cause as CheckinException)
                    is NoNetworkingException -> _noNetworking.postValue(Unit)
                    else -> _error.postValue(Unit)
                }
            }
        }
    }

    private fun handlerFormException(exception: CheckinException) {
        when (exception) {
            is CheckinException.EmptyNameException -> _nameError.postValue(stringLoader.get(R.string.empty_name_message))
            is CheckinException.EmptyEmailException -> _emailError.postValue(stringLoader.get(R.string.empty_email_message))
            is CheckinException.InvalidEmailException -> _emailError.postValue(stringLoader.get(R.string.invalid_email_message))
        }
    }
}
