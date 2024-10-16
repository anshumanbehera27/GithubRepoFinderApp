package com.anshuman.githubrepofinderapp.Model


data class GitHubRepo(
    val name: String,
    val description: String?,
    val updatedAt: String,
    val stars: Int,
    val icon: Int,
    val language: String?
)