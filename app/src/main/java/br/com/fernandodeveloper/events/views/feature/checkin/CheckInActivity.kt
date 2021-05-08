package br.com.fernandodeveloper.events.views.feature.checkin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.fernandodeveloper.domain.model.CheckInRequestBody
import br.com.fernandodeveloper.events.R
import br.com.fernandodeveloper.events.databinding.ActivityCheckInBinding
import br.com.fernandodeveloper.events.extensions.createsErrorDialog
import br.com.fernandodeveloper.events.extensions.createsSuccessDialog
import br.com.fernandodeveloper.events.views.feature.eventos.EventsActivity
import br.com.fernandodeveloper.presentation.feature.checkin.CheckInViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInActivity : AppCompatActivity() {

    private val viewModel by viewModel<CheckInViewModel>()
    private lateinit var viewDataBinding: ActivityCheckInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_check_in)
        initViews()
        inscribeObservers()
    }

    private fun initViews() {
        viewDataBinding.cancelListener = View.OnClickListener { finish() }
        viewDataBinding.checkInListener = View.OnClickListener { checkInAction() }
    }

    private fun checkInAction() {
        viewDataBinding.txtEmailError.visibility = View.GONE
        viewDataBinding.txtNameError.visibility = View.GONE

        viewModel.makeCheckIn(
            CheckInRequestBody(
                email = viewDataBinding.tietEmail.text.toString().trim(),
                eventId = intent.getStringExtra(EventsActivity.EVENT_ID)!!,
                name = viewDataBinding.tietName.text.toString().trim()
            )
        )
    }

    private fun inscribeObservers() {
        viewModel.error.observe(this, {
            createsErrorDialog(R.string.txt_something_went_wrong)
        })

        viewModel.noNetworking.observe(this, {
            createsErrorDialog(R.string.txt_no_internet)
        })

        viewModel.emailError.observe(this, {
            viewDataBinding.txtEmailError.visibility = View.VISIBLE
            viewDataBinding.txtEmailError.text = it
        })

        viewModel.nameError.observe(this, {
            viewDataBinding.txtNameError.visibility = View.VISIBLE
            viewDataBinding.txtNameError.text = it
        })

        viewModel.success.observe(this, {
            createsSuccessDialog(R.string.success_check_in_message)
        })

    }
}
