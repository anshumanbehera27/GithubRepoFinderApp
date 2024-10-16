package com.anshuman.githubrepofinderapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anshuman.githubrepofinderapp.Model.GitHubRepo
import com.anshuman.githubrepofinderapp.R

@Composable
fun GitHubRepoListItem(
    repoName: String,
    repoDescription: String,
    lastUpdated: String,
    starCount: Int,
    icon: Painter,
    language: String,
    onRepoClicked: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            // Main content of the card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 50.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = icon,
                        contentDescription = "Repo Icon",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.width(10.dp))  // Space between image and text

                    Text(
                        text = repoName,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0366D6)
                        ),
                        modifier = Modifier
                            .clickable(onClick = onRepoClicked)
                    )
                }

                Text(
                    text = repoDescription,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 16.dp, start = 10.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF673AB7))
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = language,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Last Updated: $lastUpdated",
                        style = MaterialTheme.typography.labelSmall,

                    )
                }


            }

            // Align star icon and count to the top-right corner
            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "Star Icon",
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    text = starCount.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun GitHubRepoList(repos: List<GitHubRepo>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(repos) { repo ->
            GitHubRepoListItem(
                repoName = repo.name,
                repoDescription = repo.description ?: "No description available",
                lastUpdated = repo.updatedAt,
                starCount = repo.stars,
                icon = painterResource(id = R.drawable.github), // Example icon
                language = repo.language ?: "No Language",
                onRepoClicked = { /* Handle repo click */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGitHubRepoListItem() {
    Column(Modifier.fillMaxSize()) {
        GitHubRepoListItem(
            repoName = "Sample Repository",
            repoDescription = "This is a sample repository description.",
            lastUpdated = "2024-10-16",
            starCount = 100,
            icon = painterResource(id = R.drawable.github),
            language = "Kotlin",
            onRepoClicked = {}
        )
    }
}
