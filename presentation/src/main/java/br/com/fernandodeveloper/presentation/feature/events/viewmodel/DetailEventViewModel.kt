package br.com.fernandodeveloper.presentation.feature.events.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fernandodeveloper.domain.exceptions.NoNetworkingException
import br.com.fernandodeveloper.domain.usecases.DetailEventUseCase
import br.com.fernandodeveloper.domain.utils.Either
import br.com.fernandodeveloper.presentation.br.com.fernandodeveloper.presentation.core.BaseViewModel
import br.com.fernandodeveloper.presentation.feature.events.model.EventItem
import br.com.fernandodeveloper.presentation.feature.events.model.toPresentationEventItem

class DetailEventViewModel(
    private val detailEventUseCase: DetailEventUseCase
) : BaseViewModel() {

    private val _event = MutableLiveData<EventItem>()
    val event: LiveData<EventItem> = _event

    private val _contentToBeShare = MutableLiveData<String>()
    val contentToBeShare: LiveData<String> = _contentToBeShare

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    private val _noNetworking = MutableLiveData<Unit>()
    val noNetworking: LiveData<Unit> = _noNetworking

    fun getEvent(id: String?) {
        launchSuspend {
            when (val result = detailEventUseCase.execute(id)) {
                is Either.Success -> _event.postValue(result.data.toPresentationEventItem())
                is Either.Failure -> when (result.cause) {
                    is NoNetworkingException -> _noNetworking.postValue(Unit)
                    else -> _error.postValue(Unit)
                }
            }
        }
    }

    fun shareContent() {
        if (_event.value == null) {
            _error.postValue(Unit)
        } else {
            _contentToBeShare.postValue(_event.value!!.contentToBeShare)
        }
    }
}
