package com.anshuman.githubrepofinderapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anshuman.githubrepofinderapp.R
import com.anshuman.githubrepofinderapp.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoSearchScreen(viewModel: MainViewModel, navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val repositories by viewModel.repositories.observeAsState(emptyList())
    var searchActive by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp), // Adjust padding as needed
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Updated TextField for search input
        TextField(
            value = searchQuery,
            onValueChange = { query ->
                searchQuery = query
                searchActive = query.isNotEmpty()
                if (query.isNotEmpty()) {
                    viewModel.searchRepositories(query) // Trigger search in ViewModel
                }
            },
            placeholder = {
                Text(
                    text = "Search Your Repositories",
                    color = Color.Gray // Placeholder text color
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Gray
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.Gray,
                        modifier = Modifier.clickable {
                            searchQuery = ""
                            searchActive = false
                            viewModel.searchRepositories("") // Clear the search
                        }
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp, vertical = 16.dp)
                .height(75.dp),
            singleLine = true, // Make it single-line
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                focusedTextColor = Color.Blue,  // Highlight color when typing
                unfocusedTextColor = Color.Blue,  // Keep the text color the same when unfocused
                cursorColor = Color.Black,  // Cursor color
                focusedIndicatorColor = Color.Transparent, // Hide underline when focused
                unfocusedIndicatorColor = Color.Transparent // Hide underline when unfocused
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,  // Bold text while typing
                color = Color.Blue  // Set text color to blue while typing
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    searchActive = false // Close the search when done
                }
            )
        )


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

//@Composable
//fun RepoSearchScreen(viewModel: MainViewModel, navController: NavController) {
//    var searchQuery by remember { mutableStateOf("") }
//    val repositories by viewModel.repositories.observeAsState(emptyList())
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 70.dp), // Adjust padding as needed
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        SearchBar(onSearchQueryChanged = { query ->
//            searchQuery = query
//            if (query.isNotEmpty()) {
//                viewModel.searchRepositories(query) // Trigger search in ViewModel
//            }
//        })
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Repository List
//        if (repositories.isNotEmpty()) {
//            GitHubRepoList(repos = repositories, onRepoClicked = { repo ->
//                navController.navigate("repoDetail/${repo.owner?.login}/${repo.name}")
//            })
//        } else {
//            // Show empty state or no results
//            Text("No repositories found", style = MaterialTheme.typography.bodyLarge)
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SearchBar(onSearchQueryChanged: (String) -> Unit) {
//    var searchActive by remember { mutableStateOf(false) }
//    var query by remember { mutableStateOf("") }
//
//    SearchBar(
//        query = query,
//        onQueryChange = {
//            query = it
//            onSearchQueryChanged(it) // Trigger the search function whenever the query changes
//        },
//        onSearch = {
//            searchActive = false
//        },
//        active = searchActive,
//        onActiveChange = { searchActive = it },
//        placeholder = {
//            Text(
//                text = "Search Your Repositories",
//                color = Color.Gray // Placeholder text color
//            )
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 6.dp , vertical = 16.dp)
//            .height(75.dp), // Ensure the height is appropriate for visibility
//        shape = RoundedCornerShape(12.dp),
//        colors = SearchBarDefaults.colors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer
//            // Explicitly set the colors for text and placeholder
//        ),
//        leadingIcon = {
//            Icon(
//                imageVector = Icons.Default.Search,
//                contentDescription = "Search Icon",
//                tint = Color.Gray,
//                modifier = Modifier.size(24.dp)
//            )
//        },
//        trailingIcon = {
//            if (searchActive) {
//                Icon(
//                    imageVector = Icons.Default.Close,
//                    contentDescription = "Close Icon",
//                    tint = Color.Gray,
//                    modifier = Modifier
//                        .size(24.dp)
//                        .clickable {
//                            query = ""
//                            onSearchQueryChanged("") // Clear query
//                        }
//                )
//            }
//        },
//        content = {
//            if (searchActive) {
//                // Suggestions or results area
//                Column(
//                    modifier = Modifier.padding(2.dp)
//                ) {
//                    Text(text = "Suggestions or search results can be displayed here.")
//                }
//            }
//        },
//
//    )
//}

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
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
    }
}
