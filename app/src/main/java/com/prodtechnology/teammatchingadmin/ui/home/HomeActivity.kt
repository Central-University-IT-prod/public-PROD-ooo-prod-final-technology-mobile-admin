package com.prodtechnology.teammatchingadmin.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_home)
                    as NavHostFragment).navController
        binding.bottomNavigationHome.setupWithNavController(navController)
    }
}