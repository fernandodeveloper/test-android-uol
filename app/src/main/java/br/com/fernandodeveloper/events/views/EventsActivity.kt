package br.com.fernandodeveloper.events.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.fernandodeveloper.events.R
import br.com.fernandodeveloper.events.adapters.ListEventsAdapter
import br.com.fernandodeveloper.events.databinding.ActivityEventsBinding
import br.com.fernandodeveloper.events.extensions.createsErrorDialog
import br.com.fernandodeveloper.events.extensions.createsLoadingDialog
import br.com.fernandodeveloper.presentation.feature.events.viewmodel.ListEventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsActivity : AppCompatActivity() {

    private val viewModel by viewModel<ListEventViewModel>()
    private var _binding: ActivityEventsBinding? = null
    private val binding: ActivityEventsBinding get() = _binding!!

    private lateinit var alertLoading: AlertDialog

    private val listEventsAdapter: ListEventsAdapter by lazy {
        ListEventsAdapter(object : ListEventsAdapter.ListEventsListener {
            override fun seeDetail(id: String) {
                val intent = Intent(this@EventsActivity, ScrollingActivity::class.java)
                intent.putExtra(EVENT_ID, id)
                startActivity(intent)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEventsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initViews()
        inscribeObservers()
        viewModel.getListEvents()
    }

    private fun initViews() {
        alertLoading = createsLoadingDialog()
        binding.recyclerViewEventListing.setHasFixedSize(true)
        binding.recyclerViewEventListing.adapter = listEventsAdapter
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

        viewModel.listOfEvents.observe(this, {
            alertLoading.dismiss()
            listEventsAdapter.listOfEvents = it
        })
    }

    companion object {
        const val EVENT_ID = "event_id"
    }
}