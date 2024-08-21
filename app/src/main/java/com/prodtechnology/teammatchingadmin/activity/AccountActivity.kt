package com.prodtechnology.teammatchingadmin.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.data.local.AppPrefs
import com.prodtechnology.teammatchingadmin.databinding.ActivityAccountBinding
import com.prodtechnology.teammatchingadmin.ui.home.HomeActivity

class AccountActivity : AppCompatActivity() {
    private var _binding: ActivityAccountBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppPrefs.init(applicationContext)
    }
}