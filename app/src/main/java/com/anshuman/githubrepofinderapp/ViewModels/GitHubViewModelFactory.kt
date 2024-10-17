package com.anshuman.githubrepofinderapp.ViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.anshuman.githubrepofinderapp.API.ApiInterface
import com.anshuman.githubrepofinderapp.viewmodel.MainViewModel


class MainViewModelFactory(private val apiClient: ApiInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(apiClient) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
