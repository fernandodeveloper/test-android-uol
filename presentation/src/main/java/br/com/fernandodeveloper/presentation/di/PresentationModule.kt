package br.com.fernandodeveloper.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import viewmodel.ListEventViewModel

val presentationModule = module {
    viewModel {
        ListEventViewModel(
            listEventUseCase = get()
        )
    }
}