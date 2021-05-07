package br.com.fernandodeveloper.domain.repository

import br.com.fernandodeveloper.domain.utils.Either
import br.com.fernandodeveloper.domain.model.EventResponseBodyItem

interface EventsRepository {

    suspend fun listEvents(): Either<List<EventResponseBodyItem>, Exception>

    suspend fun getEventById(id: String): Either<EventResponseBodyItem, Exception>
}
