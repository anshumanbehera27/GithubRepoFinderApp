package com.anshuman.githubrepofinderapp.API

import com.anshuman.githubrepofinderapp.Model.GitHubRepoSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("search/repositories")
    suspend fun searchRepositoriesByName(
        @Query("q") repoName: String
    ): Response<GitHubRepoSearchResponse>
}
