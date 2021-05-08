package br.com.fernandodeveloper.events.views.feature.eventos

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.fernandodeveloper.events.R
import br.com.fernandodeveloper.events.databinding.ActivityScrollingBinding
import br.com.fernandodeveloper.events.extensions.createsErrorDialog
import br.com.fernandodeveloper.events.extensions.createsLoadingDialog
import br.com.fernandodeveloper.events.views.feature.checkin.CheckInActivity
import br.com.fernandodeveloper.presentation.feature.events.model.LATITUDE
import br.com.fernandodeveloper.presentation.feature.events.model.LONGITUDE
import br.com.fernandodeveloper.presentation.feature.events.viewmodel.DetailEventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding
    private val viewModel by viewModel<DetailEventViewModel>()
    private lateinit var alertLoading: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        initViews()
        inscribeObservers()

        viewModel.getEvent(intent.getStringExtra(EventsActivity.EVENT_ID))


    }

    private fun initViews() {
        alertLoading = createsLoadingDialog()
        binding.shareListener =
            View.OnClickListener { viewModel.shareContent() }


        val mToolbar: Toolbar = findViewById(R.id.toolbar)
        mToolbar.setTitleTextColor(Color.BLACK)
        setSupportActionBar(mToolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(true)

        binding.toolbarLayout.title = getString(R.string.about_event)
    }

    private fun inscribeObservers() {
        viewModel.error.observe(this, {
            alertLoading.dismiss()
            createsErrorDialog(R.string.txt_something_went_wrong)
        })

        viewModel.noNetworking.observe(this, {
            alertLoading.dismiss()
            createsErrorDialog(R.string.txt_no_internet)
        })

        viewModel.event.observe(this, { event ->
            alertLoading.dismiss()
            binding.eventItem = event
            binding.checkInListener =
                View.OnClickListener { goToCheckInActivity(event.id) }

            binding.mapsListener =
                View.OnClickListener { goToMaps(event.latitude, event.longitude) }
        })

        viewModel.contentToBeShare.observe(this, {
            shareContent(it)
        })
    }

    private fun goToCheckInActivity(id: String) {
        val intent = Intent(this, CheckInActivity::class.java)
        intent.putExtra(EventsActivity.EVENT_ID, id)
        startActivity(intent)
    }

    private fun goToMaps(lat: String, long: String) {
        if (lat.isNotBlank() && long.isNotBlank()) {
            val latOK = lat.replace(LATITUDE, "")
            val longOK = long.replace(LONGITUDE, "")
            val gmmIntentUri = Uri.parse("google.navigation:q=$latOK,$longOK")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(packageManager)?.let {
                startActivity(mapIntent)
            }
            Toast.makeText(this, "Abrindo Google Maps...", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Ops, endereço do evento não está disponível", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareContent(text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, null))
    }


}