package com.prodtechnology.teammatchingadmin.ui.home.events

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.adapter.EventsAdapter
import com.prodtechnology.teammatchingadmin.data.local.AppPrefs
import com.prodtechnology.teammatchingadmin.data.remote.models.Event
import com.prodtechnology.teammatchingadmin.databinding.FragmentEventsBinding
import com.prodtechnology.teammatchingadmin.ui.event_info.main.EventActivity
import com.prodtechnology.teammatchingadmin.utils.ID_BUNDLE_KEY
import com.prodtechnology.teammatchingadmin.utils.status.EventsStatus

class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EventsViewModel

    private var events: MutableList<Event> = mutableListOf()


    private lateinit var adapter: EventsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            this,
            EventViewModelFactory(requireContext())
        )[EventsViewModel::class.java]

        viewModel.status.observe(viewLifecycleOwner){
            when(it){
                is EventsStatus.Succeed -> { onGetEventsSucceed(it.events) }
                is EventsStatus.Failed -> { onGetEventsFailed() }
            }
        }

        adapter = EventsAdapter(events, object : EventsAdapter.OnItemClickListener{
            override fun onItemClick(event: Event) {
                AppPrefs.setEvent(event)
                val i = Intent(requireContext(), EventActivity::class.java)
                AppPrefs.setEventId(event.id)
                startActivity(i)
            }
        })
        binding.rvEvents.adapter = adapter
        binding.rvEvents.layoutManager = LinearLayoutManager(requireContext())

        if(events.isEmpty()) {
            viewModel.getEvents(AppPrefs.getToken()!!)
        }

        binding.btnAddEvent.setOnClickListener {
            events = mutableListOf()
            findNavController().navigate(R.id.action_nav_events_to_addEventFragment2)
        }
        return binding.root
    }

    private fun onGetEventsSucceed(newEvents: List<Event>){
        events = newEvents.toMutableList()
        adapter.replaceData(events)

    }

    private fun onGetEventsFailed(){
        Toast.makeText(
            requireContext(),
            getString(R.string.error_something),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}