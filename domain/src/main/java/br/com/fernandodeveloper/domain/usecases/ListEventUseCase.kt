package br.com.fernandodeveloper.domain.usecases

import br.com.fernandodeveloper.domain.exceptions.EventsException
import br.com.fernandodeveloper.domain.model.EventResponseBodyItem
import br.com.fernandodeveloper.domain.repository.EventsRepository
import br.com.fernandodeveloper.domain.utils.Either

class ListEventUseCase(
    private val eventsRepository: EventsRepository
) {

    suspend fun execute(): Either<List<EventResponseBodyItem>, Exception> =
        when (val result = eventsRepository.listEvents()) {
            is Either.Success -> {
                if (result.data.isEmpty()) {
                    Either.Failure(EventsException.EmptyListException)
                } else {
                    Either.Success(result.data)
                }
            }
            else -> result
        }
}
