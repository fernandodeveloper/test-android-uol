package br.com.fernandodeveloper.events

import android.app.Application
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
                //TODO ADD MODULES
            )
        }
    }
}