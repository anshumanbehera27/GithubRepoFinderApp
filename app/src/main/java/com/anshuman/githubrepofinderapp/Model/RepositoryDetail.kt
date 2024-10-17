package com.anshuman.githubrepofinderapp.Model

data class RepositoryDetail(
    val name: String,
    val description: String?,
    val owner: Owner,
    val html_url: String, // Project link
    val stargazers_count: Int, // Stars count
    val updated_at: String // Last updated date
)

data class Owner(
    val login: String,
    val avatar_url: String // Owner image
)

data class Contributor(
    val login: String, // Username of the contributor
    val contributions: Int, // Number of contributions
    val avatar_url: String // URL for the contributor's avatar image
)