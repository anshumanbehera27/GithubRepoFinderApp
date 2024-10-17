package com.anshuman.githubrepofinderapp.viewmodel  // Package names should start with a lowercase letter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anshuman.githubrepofinderapp.API.ApiInterface
import com.anshuman.githubrepofinderapp.Model.GitHubRepoSearchResponse
import com.anshuman.githubrepofinderapp.Model.RepoSearchResultItem


import kotlinx.coroutines.launch
import retrofit2.Response  // Import the correct Response from Retrofit

class MainViewModel(private val apiClient: ApiInterface) : ViewModel() {

    private val _repositories = MutableLiveData<List<RepoSearchResultItem>>()
    val repositories: LiveData<List<RepoSearchResultItem>> get() = _repositories

    fun searchRepositories(query: String) {
        viewModelScope.launch {
            try {
                val response: Response<GitHubRepoSearchResponse> = apiClient.searchRepositoriesByName(query)  // Fixed response type

                if (response.isSuccessful) {
                    response.body()?.let { repoSearchResponse ->
                        if (repoSearchResponse.items.isNotEmpty()) {
                            _repositories.value = repoSearchResponse.items
                        } else {
                            _repositories.value = emptyList()
                        }
                    }
                } else {
                    _repositories.value = emptyList()
                }
            } catch (e: Exception) {
                _repositories.value = emptyList()
            }
        }
    }
}
