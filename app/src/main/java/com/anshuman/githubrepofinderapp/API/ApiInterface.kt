package com.anshuman.githubrepofinderapp.API

import com.anshuman.githubrepofinderapp.Model.Contributor
import com.anshuman.githubrepofinderapp.Model.GitHubRepoSearchResponse
import com.anshuman.githubrepofinderapp.Model.RepositoryDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("search/repositories")
    suspend fun searchRepositoriesByName(
        @Query("q") repoName: String
    ): Response<GitHubRepoSearchResponse>

    @GET("repos/{owner}/{repo}")
    suspend fun getRepositoryDetail(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): RepositoryDetail

    // Fetch contributors for the repository
    @GET("repos/{owner}/{repo}/contributors")
    suspend fun getContributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<Contributor> // Ensure this is a list of Contributor
}
