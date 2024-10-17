package com.anshuman.githubrepofinderapp.viewmodel // Updated package name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anshuman.githubrepofinderapp.API.ApiClient
import com.anshuman.githubrepofinderapp.API.ApiInterface


class MainViewModelFactory(private val apiClient: ApiInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            // Cast the ViewModel correctly and return
            return MainViewModel(apiClient) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
