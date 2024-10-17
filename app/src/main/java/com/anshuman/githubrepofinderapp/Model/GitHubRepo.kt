package com.anshuman.githubrepofinderapp.Model



data class GitHubRepoSearchResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<RepoSearchResultItem>
)

data class RepoSearchResultItem(
    val id: Int,
    val node_id: String,
    val name: String,
    val full_name: String,
    val owner: SimpleUser?,
    val description: String?,
    val updated_at: String,
    val stargazers_count: Int
)

data class SimpleUser(
    val login: String,
    val id: Int,
    val avatar_url: String
)


