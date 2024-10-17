package com.anshuman.githubrepofinderapp.viewmodel  // Package names should start with a lowercase letter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anshuman.githubrepofinderapp.API.ApiInterface
import com.anshuman.githubrepofinderapp.Model.Contributor
import com.anshuman.githubrepofinderapp.Model.GitHubRepoSearchResponse
import com.anshuman.githubrepofinderapp.Model.RepoSearchResultItem
import com.anshuman.githubrepofinderapp.Model.RepositoryDetail


import kotlinx.coroutines.launch
import retrofit2.Response  // Import the correct Response from Retrofit

class MainViewModel(private val apiClient: ApiInterface) : ViewModel() {

    // LiveData for searching repositories
    private val _repositories = MutableLiveData<List<RepoSearchResultItem>>()
    val repositories: LiveData<List<RepoSearchResultItem>> get() = _repositories

    // LiveData for repository details
    private val _repositoryDetail = MutableLiveData<RepositoryDetail?>() // Nullable for error handling
    val repositoryDetail: LiveData<RepositoryDetail?> get() = _repositoryDetail

    private val _contributors = MutableLiveData<List<Contributor>>() // Non-nullable
    val contributors: LiveData<List<Contributor>> get() = _contributors

    // Function to search repositories by name
    fun searchRepositories(query: String) {
        viewModelScope.launch {
            try {
                val response: Response<GitHubRepoSearchResponse> = apiClient.searchRepositoriesByName(query)

                if (response.isSuccessful) {
                    response.body()?.let { repoSearchResponse ->
                        _repositories.value = if (repoSearchResponse.items.isNotEmpty()) {
                            repoSearchResponse.items
                        } else {
                            emptyList()
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

    // Function to fetch repository details
    fun fetchRepositoryDetails(owner: String, repo: String) {
        viewModelScope.launch {
            try {
                // Fetching repository details
                val repoDetail = apiClient.getRepositoryDetail(owner, repo)
                _repositoryDetail.value = repoDetail

                // Fetching contributors
                val contributorList = apiClient.getContributors(owner, repo)
                _contributors.value = contributorList
            } catch (e: Exception) {
                // Handle error appropriately
                _repositoryDetail.value = null
                _contributors.value = emptyList()
            }
        }
    }
}

