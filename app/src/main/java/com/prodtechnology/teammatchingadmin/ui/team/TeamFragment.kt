package com.prodtechnology.teammatchingadmin.ui.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.adapter.MembersAdapter
import com.prodtechnology.teammatchingadmin.data.local.AppPrefs
import com.prodtechnology.teammatchingadmin.data.remote.models.TeamInfo
import com.prodtechnology.teammatchingadmin.databinding.FragmentTeamBinding
import com.prodtechnology.teammatchingadmin.utils.status.TeamStatus

class TeamFragment : Fragment() {
    private var _binding: FragmentTeamBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TeamViewModel

    private var teamId: Int? = AppPrefs.getTeamId()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(
            this
        )[TeamViewModel::class.java]
        viewModel.teamStatus.observe(viewLifecycleOwner){
            when(it){
                is TeamStatus.Succeed -> {onGetTeamSucceed(it.team)}
                is TeamStatus.Failed -> {onGetTeamFailed(it.error)}
            }
        }
        viewModel.getTeamById(AppPrefs.getToken()!!, requireArguments().getInt("TeamId"))
        return binding.root
    }

    private fun onGetTeamSucceed(team: TeamInfo) {
        var mobile = 0
        var frontend = 0
        var backend = 0
        val stack = mutableSetOf<String>()
        for(user in team.users){
            when(user.profession){
                "mobile" -> {mobile++}
                "frontend" -> {frontend++}
                else -> {backend++}
            }
            stack.addAll(user.skills.map { it.title })
        }

        binding.aboutTeam.text = team.description
        binding.frontendUsers.text = frontend.toString()
        binding.backendUsers.text = backend.toString()
        binding.mobileUsers.text = mobile.toString()
        binding.stackTeam.text = stack.joinToString()

        val adapter = MembersAdapter(false)
        adapter.submitList(team.users)
        binding.rcTeam.adapter = adapter
        binding.rcTeam.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun onGetTeamFailed(error: String) {

    }
}