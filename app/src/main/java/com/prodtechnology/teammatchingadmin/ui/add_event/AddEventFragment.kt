package com.prodtechnology.teammatchingadmin.ui.add_event

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.data.local.AppPrefs
import com.prodtechnology.teammatchingadmin.data.remote.models.Event
import com.prodtechnology.teammatchingadmin.databinding.FragmentAddEventBinding
import com.prodtechnology.teammatchingadmin.utils.status.AddEventStatus
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddEventFragment : Fragment() {
    private var _binding: FragmentAddEventBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AddEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEventBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            this,
            AddEventViewModelFactory(requireContext())
        )[AddEventViewModel::class.java]

        binding.newEventCalendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val c = Calendar.getInstance()
            c.set(year, month, dayOfMonth)
            binding.newEventCalendar.date = c.timeInMillis
        }

        binding.btnCreateNewEvent.setOnClickListener {
            binding.inputLayoutNewEventTitle.isErrorEnabled = false
            binding.inputLayoutNewEventMaxQty.isErrorEnabled = false

            if(binding.etNewEventTitle.text.isEmpty()){
                binding.inputLayoutNewEventTitle.error = getString(R.string.error_empty_field)
            }
            if(binding.etNewEventMaxQty.text.isEmpty()){
                binding.inputLayoutNewEventMaxQty.error = getString(R.string.error_empty_field)
            }
            if(!binding.inputLayoutNewEventMaxQty.isErrorEnabled && !binding.inputLayoutNewEventTitle.isErrorEnabled){
                val f = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                Log.d("Formatted Time", f.format(Date(binding.newEventCalendar.date)))
                viewModel.addEvent(AppPrefs.getToken()!!, Event(
                    0,
                    binding.etNewEventTitle.text.toString(),
                    binding.etNewEventMaxQty.text.toString().toInt(),
                    f.format(Date(binding.newEventCalendar.date))
                )
                )
            }
        }

        viewModel.status.observe(viewLifecycleOwner){
            when(it){
                is AddEventStatus.Succeed -> {onAddEventSucceed()}
                is AddEventStatus.Failed -> {onAddEventFailed(it.error)}
            }
        }
        return binding.root
    }

    private fun onAddEventFailed(error: String) {
        Toast.makeText(
            requireContext(),
            error,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun onAddEventSucceed() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}