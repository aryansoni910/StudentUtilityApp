package com.example.project_1.Presentation.Screen.StudentScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.project_1.Data.Network.Article
import com.example.project_1.Presentation.ViewModel.Project1ViewModel

@Composable
fun StudentNewsScreen(modifier: Modifier = Modifier, viewModel:Project1ViewModel = hiltViewModel()) {
    // Accessing the state from the ViewModel safely
    val newsState = viewModel.res.value

    // If newsState is null or has no articles, show a loading or empty state
    if (newsState == null || newsState.articles.isEmpty()) {
        Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            Text("Loading news...", style = MaterialTheme.typography.bodyLarge)
        }
    } else {
        // If newsState is not null, display articles
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(newsState.articles) { article ->
                NewsCard(article = article)
            }
        }
    }
}

@Composable
fun NewsCard(article: Article) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Image using AsyncImage from Coil
            article.urlToImage?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "News Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium
                ,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Description if available
            article.description?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = it, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}




