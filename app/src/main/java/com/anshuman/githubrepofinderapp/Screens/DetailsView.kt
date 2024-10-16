package com.anshuman.githubrepofinderapp.Screens



import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anshuman.githubrepofinderapp.Model.Contributor

import com.anshuman.githubrepofinderapp.Model.RepositoryDetail
import com.anshuman.githubrepofinderapp.R

@Composable
fun RepositoryDetailsView(
    repo: RepositoryDetail,
    onProjectLinkClicked: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // Owner Avatar
            Image(
                painter = painterResource(id = R.drawable.user), // Replace with actual image resource or URL
                contentDescription = "Owner Avatar",
                modifier = Modifier
                    .size(100.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Repository Name
            Text(
                text = repo.name,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Project Link
            ClickableText(
                text = buildAnnotatedString {
                    append("Project Link: ")
                    pushStringAnnotation("link", repo.projectLink)
                    append(repo.projectLink)
                    pop()
                },
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                onClick = { offset ->
                    // Handle link click if it is clicked
                    // Logic to open link
                    onProjectLinkClicked()
                },
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Repository Description
            Text(
                text = repo.description ?: "No description available",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Contributors Label
            Text(
                text = "Contributors",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        items(repo.contributors) { contributor ->
            ContributorItem(contributor)
        }
    }
}

@Composable
fun ContributorItem(contributor: Contributor) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Contributor Avatar
        Image(
            painter = painterResource(id = R.drawable.user), // Replace with actual image resource or URL
            contentDescription = "Contributor Avatar",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Contributor Name
        Text(
            text = contributor.name,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewRepositoryDetailsView() {
    val sampleRepo = RepositoryDetail(
        ownerAvatar = "sample_avatar_url", // Placeholder for the owner's avatar
        name = "Sample Repository",
        projectLink = "https://github.com/sample-repo",
        description = "This is a sample repository description to showcase the details view.",
        contributors = listOf(
            Contributor(name = "Contributor One", avatar = "contributor_one_avatar_url"),
            Contributor(name = "Contributor Two", avatar = "contributor_two_avatar_url")
        )
    )

    RepositoryDetailsView(repo = sampleRepo) {
        // Handle project link click (e.g., open in a web browser)
    }
}