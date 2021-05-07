package br.com.fernandodeveloper.data.repository

import br.com.fernandodeveloper.data.api.EventsAPI
import br.com.fernandodeveloper.data.mapper.toDomainEventResponseBodyItem
import br.com.fernandodeveloper.data.mapper.toDomainListEventsItemsListResponseBody
import br.com.fernandodeveloper.domain.exceptions.GenericException
import br.com.fernandodeveloper.domain.exceptions.NoNetworkingException
import br.com.fernandodeveloper.domain.model.EventResponseBodyItem
import br.com.fernandodeveloper.domain.repository.EventsRepository
import br.com.fernandodeveloper.domain.utils.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class EventsRepositoryImpl(
    private val eventsAPI: EventsAPI
) : EventsRepository {

    override suspend fun listEvents(): Either<List<EventResponseBodyItem>, Exception> =
        withContext(Dispatchers.IO) {
            try {
                val response = eventsAPI.getListEvents()
                when (response.code()) {
                    200 -> Either.Success(
                        response.body()!!.toDomainListEventsItemsListResponseBody()
                    )
                    else -> Either.Failure(GenericException)
                }
            } catch (e: UnknownHostException) {
                Either.Failure(NoNetworkingException)
            }
        }

    override suspend fun getEventById(id: String): Either<EventResponseBodyItem, Exception> =
        withContext(Dispatchers.IO) {
            try {
                val response = eventsAPI.getEventById(id)
                when (response.code()) {
                    200 -> Either.Success(
                        response.body()!!.toDomainEventResponseBodyItem()
                    )
                    else -> Either.Failure(GenericException)
                }
            } catch (e: UnknownHostException) {
                Either.Failure(NoNetworkingException)
            }
        }
}
