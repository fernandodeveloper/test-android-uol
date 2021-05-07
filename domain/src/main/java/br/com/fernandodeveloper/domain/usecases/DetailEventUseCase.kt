package br.com.fernandodeveloper.domain.usecases

import br.com.fernandodeveloper.domain.model.EventResponseBodyItem
import br.com.fernandodeveloper.domain.repository.EventsRepository
import br.com.fernandodeveloper.domain.utils.Either

class DetailEventUseCase(
    private val eventsRepository: EventsRepository
) {

    suspend fun execute(id: String?): Either<EventResponseBodyItem, Exception> {
        if (id.isNullOrEmpty()) throw IllegalArgumentException()
        return eventsRepository.getEventById(id)
    }
}
