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
import androidx.compose.ui.unit.dp


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
                            .background(Color(0x2A673AB7))
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

//
//@Composable
//fun GitHubRepoList(repos: List<GitHubRepo>) {
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(repos) { repo ->
//            GitHubRepoListItem(
//                repoName = repo.name ?: "No Name", // Provide a default if repo.name is null
//                repoDescription = repo.description ?: "No Description", // Provide a default if repo.description is null
//                lastUpdated = repo.updatedAt ?: "Not Available", // Provide a default if repo.updatedAt is null
//                starCount = repo.stars ?: 0, // Use 0 as a default if repo.stars is null
//                icon = painterResource(id = repo.icon), // Convert Int to Painter using painterResource
//                language = repo.language ?: "Unknown", // Provide a default if repo.language is null
//                onRepoClicked = {  } // Handle repo click event
//            )
//        }
//    }
//}



//
//@Composable
//fun PreviewGitHubRepoListItem() {
//    val sampleRepos = listOf(
//        GitHubRepo(
//            name = "Sample Repository 1",
//            description = "This is a sample repository description 1.",
//            updatedAt = "2024-10-16",
//            stars = 100,
//            language = "Kotlin",
//            icon = R.drawable.github // Use the actual drawable resource ID
//        ),
//        GitHubRepo(
//            name = "Sample Repository 2",
//            description = "This is a sample repository description 2.",
//            updatedAt = "2024-10-16",
//            stars = 150,
//            language = "Java",
//            icon = R.drawable.github // Use the actual drawable resource ID
//        ),
//        GitHubRepo(
//            name = "Sample Repository 3",
//            description = "This is a sample repository description 3.",
//            updatedAt = "2024-10-16",
//            stars = 200,
//            language = "Python",
//            icon = R.drawable.github // Use the actual drawable resource ID
//        )
//    )
//
//
//
//}
