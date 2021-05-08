package br.com.fernandodeveloper.events.views.feature.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import br.com.fernandodeveloper.events.R
import br.com.fernandodeveloper.events.views.feature.eventos.EventsActivity

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            showMainActivity()
        }, 2000)
    }

    private fun showMainActivity() {
        val intent = Intent(this, EventsActivity::class.java)
        startActivity(intent)
        finish()
    }

}