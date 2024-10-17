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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.anshuman.githubrepofinderapp.Model.RepoSearchResultItem


import com.anshuman.githubrepofinderapp.R


@Composable
fun GitHubRepoList(repos: List<RepoSearchResultItem>, onRepoClicked: (RepoSearchResultItem) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(repos) { repo ->
            GitHubRepoListItem(
                repo = repo,
                onRepoClicked = { onRepoClicked(repo) }
            )
        }
    }
}

@Composable
fun GitHubRepoListItem(
    repo: RepoSearchResultItem,
    onRepoClicked: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp).clickable(onClick = onRepoClicked)
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
                    // Avatar Image with Coil
                    Image(
                        painter = rememberImagePainter(data = repo.owner?.avatar_url),
                        contentDescription = "Owner Avatar",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(10.dp))  // Space between image and text

                    // Repository Name
                    Text(
                        text = repo.name,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0366D6)
                        ),
                        modifier = Modifier
                            .clickable(onClick = onRepoClicked)
                    )
                }

                // Repository Description
                Text(
                    text = repo.description ?: "No description available",
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
                            .background(Color(0x2A673AB7))
                    )




                    // Last Updated Date
                    Text(
                        text = "Last Updated: ${repo.updated_at}",
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

                // Star count
                Text(
                    text = repo.stargazers_count.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}








