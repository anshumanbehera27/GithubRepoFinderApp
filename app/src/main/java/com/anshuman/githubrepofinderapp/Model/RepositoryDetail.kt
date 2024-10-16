package com.anshuman.githubrepofinderapp.Model

data class RepositoryDetail(
    val ownerAvatar: String, // URL or resource for the owner's avatar
    val name: String,
    val projectLink: String,
    val description: String?,
    val contributors: List<Contributor>
)

data class Contributor(
    val name: String,
    val avatar: String // URL or resource for the contributor's avatar
)
