package com.anshuman.githubrepofinderapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.anshuman.githubrepofinderapp.R




@Composable
fun HomeScreen(navController: NavController) {

//    val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(ApiClient.getClient()))
//    val repositories by viewModel.repositories.observeAsState(emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar()

        SearchBar(onSearchQueryChanged = { query ->
           // viewModel.searchRepositories(query) // Call the ViewModel's search function
        })





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
            query = it
            onSearchQueryChanged(it)
        },
        onSearch = {
            // Optional: Handle what happens on search
            searchActive = false
        },
        active = searchActive,
        onActiveChange = { searchActive = it },
        placeholder = { Text(text = "Search Your Repositories") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .height(65.dp),
        shape = RoundedCornerShape(12.dp),
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { searchActive = !searchActive }
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
            // Show search results if needed
            if (searchActive) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    // You can show suggestions based on query here
                    Text("Suggestions go here!")
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



@Preview(showBackground = true)
@Composable
fun homescreenpreview(){
    HomeScreen(navController = rememberNavController())
}