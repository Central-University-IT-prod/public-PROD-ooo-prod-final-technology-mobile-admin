package com.prodtechnology.teammatchingadmin.ui.event_info.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.data.local.AppPrefs
import com.prodtechnology.teammatchingadmin.data.remote.models.EventStatistics
import com.prodtechnology.teammatchingadmin.data.remote.models.UploadRequest
import com.prodtechnology.teammatchingadmin.databinding.FragmentStatisticsBinding
import com.prodtechnology.teammatchingadmin.utils.status.StatisticsStatus
import com.prodtechnology.teammatchingadmin.utils.status.UploadStatus

class StatisticsFragment : Fragment() {

    private lateinit var viewModel: StatisticsViewModel

    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(
            this,
            StatisticsViewModelFactory(requireContext())
        )[StatisticsViewModel::class.java]
        viewModel.status.observe(viewLifecycleOwner){
            binding.llStatisticsLoading.visibility = View.GONE
            when(it){
                is StatisticsStatus.Succeed -> {onGetStatisticsSucceed(it.data)}
                is StatisticsStatus.Failed -> {onGetStatisticsFailed(it.error)}
            }
        }
        viewModel.uploadStatus.observe(viewLifecycleOwner){
            binding.llStatisticsLoading.visibility = View.GONE
            when(it){
                is UploadStatus.Succeed -> {onUploadSucceed()}
                is UploadStatus.Failed -> {onUploadFailed(it.error)}
            }
        }

        viewModel.getStatistics(AppPrefs.getToken()!!, AppPrefs.getEvent()!!.id)

        binding.btnUploadUsersData.setOnClickListener {
            if(binding.etStatisticsUploadUsers.text.isNotEmpty()){
                binding.llStatisticsLoading.visibility = View.VISIBLE
                viewModel.uploadUsers(AppPrefs.getToken()!!,
                    UploadRequest(
                        binding.etStatisticsUploadUsers.text.toString(),
                        AppPrefs.getEvent()!!.id
                    )
                )
            }else{
                binding.inputLayoutStatisticsUploadUsers.error = getString(R.string.error_empty_field)
            }
        }

        binding.tvStatisticsTitle.text = AppPrefs.getEvent()!!.title

        return binding.root
    }

    private fun onUploadSucceed() {
        Toast.makeText(
            requireContext(),
            getString(R.string.message_added_successfully),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onUploadFailed(error: String) {
        Toast.makeText(
            requireContext(),
            error,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onGetStatisticsFailed(error: String) {
        Toast.makeText(
            requireContext(),
            error,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun onGetStatisticsSucceed(data: EventStatistics) {
        binding.tvStatisticsBackend.text = data.backendQty.toString()
        binding.tvStatisticsFrontend.text = data.frontedQty.toString()
        binding.tvStatisticsMobile.text = data.mobileQty.toString()
        binding.tvStatisticsTeamsQty.text = data.teamsQty.toString()
        binding.tvStatisticsTitle.text = AppPrefs.getEvent()!!.title
        binding.tvStatisticsUsersQty.text = data.usersQty.toString()
    }
}