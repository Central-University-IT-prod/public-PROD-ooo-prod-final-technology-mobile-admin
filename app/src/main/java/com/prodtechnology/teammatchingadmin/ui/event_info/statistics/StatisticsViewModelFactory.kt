package com.prodtechnology.teammatchingadmin.ui.event_info.statistics

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StatisticsViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StatisticsViewModel(context) as T
    }
}