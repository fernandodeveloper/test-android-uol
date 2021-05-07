package br.com.fernandodeveloper.presentation.di

import br.com.fernandodeveloper.presentation.StringLoader
import br.com.fernandodeveloper.presentation.feature.checkin.CheckInViewModel
import br.com.fernandodeveloper.presentation.feature.events.viewmodel.DetailEventViewModel
import br.com.fernandodeveloper.presentation.feature.events.viewmodel.ListEventViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    single {
        StringLoader(androidContext())
    }

    viewModel {
        ListEventViewModel(
            listEventUseCase = get()
        )
    }

    viewModel {
        DetailEventViewModel(
            detailEventUseCase = get()
        )
    }

    viewModel {
        CheckInViewModel(
            checkInUseCase = get(),
            stringLoader = get()
        )
    }
}