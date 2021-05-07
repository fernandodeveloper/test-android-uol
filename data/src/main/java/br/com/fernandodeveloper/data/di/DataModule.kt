package br.com.fernandodeveloper.data.di

import br.com.fernandodeveloper.data.api.ApiBase
import br.com.fernandodeveloper.data.api.CheckInAPI
import br.com.fernandodeveloper.data.repository.CheckInRepositoryImpl
import br.com.fernandodeveloper.domain.repository.CheckInRepository
import org.koin.dsl.module

val dataModule = module {
    single {
        ApiBase.createWebService<CheckInAPI>(
            okHttpClient = get()
        )
    }


    factory<CheckInRepository> {
        CheckInRepositoryImpl(
            checkInAPI = get()
        )
    }
}