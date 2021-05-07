package br.com.fernandodeveloper.presentation.feature.events.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fernandodeveloper.domain.exceptions.NoNetworkingException
import br.com.fernandodeveloper.domain.usecases.ListEventUseCase
import br.com.fernandodeveloper.domain.utils.Either
import br.com.fernandodeveloper.presentation.br.com.fernandodeveloper.presentation.core.BaseViewModel
import br.com.fernandodeveloper.presentation.feature.events.model.EventItem
import br.com.fernandodeveloper.presentation.feature.events.model.toPresentationEventItemList

class ListEventViewModel(
    private val listEventUseCase: ListEventUseCase
) : BaseViewModel() {

    private val _listOfEvents = MutableLiveData<List<EventItem>>()
    val listOfEvents: LiveData<List<EventItem>> = _listOfEvents

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    private val _noNetworking = MutableLiveData<Unit>()
    val noNetworking: LiveData<Unit> = _noNetworking

    fun getListEvents() {
        launchSuspend {
            when (val result = listEventUseCase.execute()) {
                is Either.Success -> _listOfEvents.postValue(result.data.toPresentationEventItemList())
                is Either.Failure -> when (result.cause) {
                    is NoNetworkingException -> _noNetworking.postValue(Unit)
                    else -> _error.postValue(Unit)
                }
            }
        }
    }
}