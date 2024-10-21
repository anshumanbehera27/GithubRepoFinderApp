package com.anshuman.githubrepofinderapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter

import com.anshuman.githubrepofinderapp.API.ApiClient
import com.anshuman.githubrepofinderapp.Model.Contributor
import com.anshuman.githubrepofinderapp.Screens.ContributorItem
import com.anshuman.githubrepofinderapp.Screens.Navigation


import com.anshuman.githubrepofinderapp.Screens.RepoSearchScreen
import com.anshuman.githubrepofinderapp.Screens.TopAppBar

import com.anshuman.githubrepofinderapp.ui.theme.GithubRepoFinderAppTheme
import com.anshuman.githubrepofinderapp.viewmodel.MainViewModel
import com.anshuman.githubrepofinderapp.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GithubRepoFinderAppTheme {
                Navigation()


            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    // Use a surface for background color
    Scaffold(
        topBar = { TopAppBar()}
    ) {

        val navController = rememberNavController()
        AppNavGraph(navController = navController)

    }





    }



@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "repoSearch") {
        composable("repoSearch") {
            val apiClient = ApiClient.getClient()
            val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(apiClient))
            RepoSearchScreen(viewModel, navController)
        }
        composable("repoDetail/{owner}/{repo}") { backStackEntry ->
            val owner = backStackEntry.arguments?.getString("owner") ?: ""
            val repo = backStackEntry.arguments?.getString("repo") ?: ""
            RepoDetailScreen(owner = owner, repo = repo)
        }
    }
}


@Composable
fun RepoDetailScreen(owner: String, repo: String) {
    val apiClient = ApiClient.getClient()
    val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(apiClient))

    val repositoryDetail by viewModel.repositoryDetail.observeAsState()
    val contributors by viewModel.contributors.observeAsState(emptyList())
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchRepositoryDetails(owner, repo)
    }

    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(top = 85.dp)) {
        // Display repository details

        if (repositoryDetail != null) {
            val repoDetail = repositoryDetail!!

            Card(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(all = 12.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically // Align items vertically
                ) {
                    // Enlarged Repository Image (Owner Avatar)
                    Image(
                        painter = rememberImagePainter(repoDetail.owner.avatar_url),
                        contentDescription = "Owner Avatar",
                        modifier = Modifier
                            .size(100.dp) // Increased image size
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )

                    Spacer(modifier = Modifier.width(26.dp)) // Space between image and text

                    // Repository Name
                    Text(
                        text = repoDetail.name,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold, // Making the text bold
                        modifier = Modifier.padding(vertical = 8.dp)

                    )
                }
            }



            // Repository Description
            Card(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(all = 12.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    // Description Heading
                    Text(
                        text = "Description:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold, // Bold heading for emphasis
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Description Text
                    Text(
                        text = repoDetail.description ?: "No description available.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 12.dp),
                shape = RoundedCornerShape(12.dp),

            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    // Project Link Heading
                    Text(
                        text = "Project Link:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold, // Bold for heading emphasis
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Clickable Project Link
                    Text(
                        text = repoDetail.html_url,
                        color = Color.Black, // Highlight the link in blue
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .clickable {
                                // Open the project link in a browser
                                val intent =
                                    Intent(Intent.ACTION_VIEW, Uri.parse(repoDetail.html_url))
                                context.startActivity(intent)
                            }
                            .padding(bottom = 8.dp)
                    )
                }
            }


            // Contributors Section
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Contributors:", style = MaterialTheme.typography.bodyLarge )
            LazyColumn {
                items(contributors) { contributor ->
                    ContributorItem(contributor)
                }
            }
        } else {
            // Show loading or error state
            Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge
                , textAlign = TextAlign.Center  )
        }
    }
}





