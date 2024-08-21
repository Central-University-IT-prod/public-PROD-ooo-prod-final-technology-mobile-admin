package com.prodtechnology.teammatchingadmin.ui.event_info.teams_members

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.adapter.SwapAdapter
import com.prodtechnology.teammatchingadmin.databinding.FragmentTeamsMembersBinding

class TeamsMembersFragment : Fragment() {
    private val listSwap = listOf(
        TeamsFragment.newInstance(),
        MembersFragment.newInstance()
    )
    private lateinit var listTabLayout: List<String>
    private var _binding: FragmentTeamsMembersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamsMembersBinding.inflate(inflater, container, false)
        listTabLayout = listOf(
            requireContext().getString(R.string.text_teams),
            requireContext().getString(R.string.text_members)
        )

        initSwap()
        return binding.root
    }

    private fun initSwap() = with(binding){
        val adapter = SwapAdapter(activity as FragmentActivity, listSwap)
        vp.adapter = adapter
        TabLayoutMediator(tabLayout, vp){
            tab, pos -> tab.text = listTabLayout[pos]
        }.attach()
    }
}