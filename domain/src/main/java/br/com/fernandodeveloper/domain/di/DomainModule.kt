package br.com.fernandodeveloper.domain.di

import br.com.fernandodeveloper.domain.usecases.CheckInUseCase
import br.com.fernandodeveloper.domain.usecases.DetailEventUseCase
import br.com.fernandodeveloper.domain.usecases.ListEventUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        CheckInUseCase(
            checkInRepository = get()
        )
    }

    factory {
        DetailEventUseCase(
            eventsRepository = get()
        )
    }

    factory {
        ListEventUseCase(
            eventsRepository = get()
        )
    }

}