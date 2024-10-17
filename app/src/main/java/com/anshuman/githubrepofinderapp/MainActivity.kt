package com.anshuman.githubrepofinderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider

import com.anshuman.githubrepofinderapp.API.ApiClient

import com.anshuman.githubrepofinderapp.Model.RepoSearchResultItem


import com.anshuman.githubrepofinderapp.ViewModels.MainViewModelFactory

import com.anshuman.githubrepofinderapp.ui.theme.GithubRepoFinderAppTheme
import com.anshuman.githubrepofinderapp.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GithubRepoFinderAppTheme {
                val apiClient = ApiClient.getClient()
                val viewModel = ViewModelProvider(this, MainViewModelFactory(apiClient)).get(
                    MainViewModel::class.java)
                RepoSearchScreen(viewModel)


            }
        }
    }
}
@Composable
fun RepoSearchScreen(viewModel: MainViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    val repositories by viewModel.repositories.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Repository") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.searchRepositories(searchQuery) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        repositories.forEach { repo ->
            RepoItem(repo = repo)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun RepoItem(repo: RepoSearchResultItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Name: ${repo.name}", modifier = Modifier.padding(bottom = 4.dp))
        Text(text = "Description: ${repo.description ?: "No description available"}", modifier = Modifier.padding(bottom = 4.dp))
        Text(text = "Updated At: ${repo.updated_at}", modifier = Modifier.padding(bottom = 4.dp))
        Text(text = "Stars: ${repo.stargazers_count}", modifier = Modifier.padding(bottom = 4.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Placeholder image
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .padding(4.dp)
        )
    }
}