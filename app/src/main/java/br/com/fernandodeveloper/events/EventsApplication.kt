package br.com.fernandodeveloper.events

import android.app.Application
import br.com.fernandodeveloper.data.di.dataModule
import br.com.fernandodeveloper.domain.di.domainModule
import br.com.fernandodeveloper.events.di.aplicationModule
import br.com.fernandodeveloper.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class EventsApplication : Application() {

    companion object {
        lateinit var instance: EventsApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@EventsApplication)
            modules(
                aplicationModule,
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}