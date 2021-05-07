package br.com.fernandodeveloper.data.di

import br.com.fernandodeveloper.data.api.ApiBase
import br.com.fernandodeveloper.data.api.CheckInAPI
import br.com.fernandodeveloper.data.api.EventsAPI
import br.com.fernandodeveloper.data.repository.CheckInRepositoryImpl
import br.com.fernandodeveloper.data.repository.EventsRepositoryImpl
import br.com.fernandodeveloper.domain.repository.CheckInRepository
import br.com.fernandodeveloper.domain.repository.EventsRepository
import org.koin.dsl.module

val dataModule = module {
    factory {
        ApiBase.provideHttpClient()
    }

    single {
        ApiBase.createWebService<CheckInAPI>(
            okHttpClient = get()
        )
    }

    single {
        ApiBase.createWebService<EventsAPI>(
            okHttpClient = get()
        )
    }

    factory<CheckInRepository> {
        CheckInRepositoryImpl(
            checkInAPI = get()
        )
    }

    factory<EventsRepository> {
        EventsRepositoryImpl(
            eventsAPI = get()
        )
    }
}