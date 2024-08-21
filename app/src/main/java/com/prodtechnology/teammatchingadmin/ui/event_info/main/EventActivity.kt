package com.prodtechnology.teammatchingadmin.ui.event_info.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.databinding.ActivityEventBinding
import com.prodtechnology.teammatchingadmin.utils.ID_BUNDLE_KEY

class EventActivity : AppCompatActivity() {

    private var _binding: ActivityEventBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_event) as NavHostFragment).navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}