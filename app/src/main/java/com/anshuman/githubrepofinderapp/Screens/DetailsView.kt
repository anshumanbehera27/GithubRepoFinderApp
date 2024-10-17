package com.anshuman.githubrepofinderapp.Screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.compose.rememberImagePainter
import com.anshuman.githubrepofinderapp.API.ApiClient
import com.anshuman.githubrepofinderapp.API.ApiInterface
import com.anshuman.githubrepofinderapp.Model.Contributor

import com.anshuman.githubrepofinderapp.viewmodel.MainViewModel
import com.anshuman.githubrepofinderapp.viewmodel.MainViewModelFactory

//@Composable
//fun RepoDetailScreen(owner: String, repo: String) {
//    // Create an instance of ApiClient, which implements ApiInterface
//
//    // Initialize the API client and ViewModel
//
//
//    // Observe repository details and contributors
//    val repositoryDetail by viewModel.repositoryDetail.observeAsState()
//    val contributors by viewModel.contributors.observeAsState(emptyList())
//    val context = LocalContext.current
//
//    // Fetch repository details when the screen is opened
//    LaunchedEffect(Unit) {
//        viewModel.fetchRepositoryDetails(owner, repo)
//    }
//
//    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//        // Display repository details
//        if (repositoryDetail != null) {
//            val repoDetail = repositoryDetail!!
//
//            // Repository Image (Owner Avatar)
//            Image(
//                painter = rememberImagePainter(repoDetail.owner.avatar_url),
//                contentDescription = "Owner Avatar",
//                modifier = Modifier
//                    .size(64.dp)
//                    .clip(CircleShape)
//                    .border(1.dp, Color.Gray, CircleShape)
//            )
//
//            // Repository Name
//            Text(
//                text = repoDetail.name,
//                style = MaterialTheme.typography.bodyLarge,
//                modifier = Modifier.padding(vertical = 8.dp)
//            )
//
//            // Repository Description
//            Text(
//                text = repoDetail.description ?: "No description available.",
//                style = MaterialTheme.typography.bodyMedium
//            )
//
//            // Project Link
//            Text(
//                text = "Project Link: ",
//                style = MaterialTheme.typography.bodyLarge,
//                modifier = Modifier.padding(top = 8.dp)
//            )
//            Text(
//                text = repoDetail.html_url,
//                color = Color.Blue,
//                style = MaterialTheme.typography.bodyLarge,
//                modifier = Modifier.clickable {
//                    // Open the project link in a browser
//                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repoDetail.html_url))
//                    context.startActivity(intent)
//                }
//            )
//
//            // Contributors Section
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(text = "Contributors:", style = MaterialTheme.typography.bodyLarge)
////            LazyColumn {
////                items(contributors) { contributor ->
////                    ContributorItem(contributor) // Pass a single Contributor object
////                }
////            }
////        } else {
////            // Show loading or error state
////            Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
////        }
//        }
//    }

    @Composable
    fun ContributorItem(contributor: Contributor) { // Take a single Contributor
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Contributor Avatar
            Image(
                painter = rememberImagePainter(contributor.avatar_url), // Load actual image from URL
                contentDescription = "Contributor Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Contributor Name
            Column {
                Text(text = contributor.login, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = "${contributor.contributions} contributions",
                    style = MaterialTheme.typography.bodySmall
                ) // Correct usage
            }
        }
    }

