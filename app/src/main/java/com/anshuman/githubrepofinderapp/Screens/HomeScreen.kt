package com.anshuman.githubrepofinderapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anshuman.githubrepofinderapp.R
import com.anshuman.githubrepofinderapp.viewmodel.MainViewModel



@Composable
fun RepoSearchScreen(viewModel: MainViewModel , navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val repositories by viewModel.repositories.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Search Bar
        SearchBar(onSearchQueryChanged = { query ->
            searchQuery = query
            if (query.isNotEmpty()) {
                viewModel.searchRepositories(query) // Trigger search in ViewModel
            }
        })

        Spacer(modifier = Modifier.height(16.dp))

        // Repository List
        if (repositories.isNotEmpty()) {
            GitHubRepoList(repos = repositories, onRepoClicked = { repo ->
                navController.navigate("repoDetail/${repo.owner?.login}/${repo.name}")
            })
        } else {
            // Show empty state or no results
            Text("No repositories found", style = MaterialTheme.typography.bodyLarge)
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(onSearchQueryChanged: (String) -> Unit) {
    var searchActive by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }

    SearchBar(
        query = query,
        onQueryChange = {
            query =  it
            onSearchQueryChanged(it) // Trigger the search function whenever the query changes
        },
        onSearch = {
            searchActive = false
        },
        active = searchActive,
        onActiveChange = { searchActive = it },
        placeholder = {
            Text(
                text = "Search Your Repositories",
                color = Color.Gray // Placeholder text color
            )
        },
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 16.dp)
            .height(95.dp), // Adjusta height for better visibility
        shape = RoundedCornerShape(12.dp),
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,

        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        },
        trailingIcon = {
            if (searchActive) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            query = ""
                            onSearchQueryChanged("") // Clear query
                        }
                )
            }
        },
        content = {
            if (searchActive) {
                // Suggestions or results area
                Column(
                    modifier = Modifier.padding(2.dp)
                ) {
                    Text(text = "Suggestions or search results can be displayed here.")
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        // TopAppBar to replace the Toolbar
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Image logo like GitHub icon
                    Image(
                        painter = painterResource(id = R.drawable.github),
                        contentDescription = "GitHub Logo",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 16.dp),
                        contentScale = ContentScale.Fit
                    )

                    // App name text
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer ))
    }
}



