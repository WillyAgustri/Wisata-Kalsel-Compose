@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.wisatakalsel.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wisatakalsel.injection.Injection
import com.example.wisatakalsel.ui.common.UiState
import com.example.wisatakalsel.ui.viewmodel.DetailViewModel
import com.example.wisatakalsel.ui.viewmodel.ViewModelFactory
import io.eyram.iconsax.IconSax


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    tourId: Int,
    navigateBack: () -> Unit,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier

                    .shadow(
                        elevation = 3.dp,
                        spotColor = MaterialTheme.colorScheme.onBackground
                    ),
                title = {
                    Text(
                        text = "Detail Tour",
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 17.sp),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },

                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            painter = painterResource(id = IconSax.Linear.ArrowLeft),
                            contentDescription = "Home",
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }
            )
        }

    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        viewModel.getTourById(tourId)
                    }

                    is UiState.Success -> {
                        val data = uiState.data
                        DetailData(
                            image = data.image,
                            id = data.id,
                            location = data.location,
                            name = data.name,
                            description = data.description,
                            isFavorite = data.isFavorite,
                            onFavoriteButtonClicked = { id, state ->
                                viewModel.updateTour(id, state)
                            }
                        )
                    }

                    is UiState.Error -> {}
                }
            }
        }


    }

}

@Composable
fun DetailData(
    id: Int,
    name: String,
    @DrawableRes image: Int,
    location: String,
    description: String,
    isFavorite: Boolean,
    onFavoriteButtonClicked: (id: Int, state: Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("scrollToBottom")
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = IconSax.Linear.Image),
                        contentDescription = "Icon Name",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Text(
                        text = name,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 20.sp),
                        color = MaterialTheme.colorScheme.onBackground,

                        )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = IconSax.Linear.Location),
                        contentDescription = "Icon Location",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Text(
                        text = location,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
            Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 17.sp),
                color = MaterialTheme.colorScheme.secondary,
                lineHeight = 28.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        IconButton(
            onClick = {
                onFavoriteButtonClicked(id, isFavorite)
            },
            modifier = Modifier
                .padding(end = 16.dp, bottom = 8.dp)
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
                .size(40.dp)
                .background(Color.LightGray)
                .testTag("favorite_detail_button")
        ) {
            Icon(
                imageVector = if (!isFavorite) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                contentDescription = if (!isFavorite) "Add To Favorite" else (
                        "Delete To Favorite"),
                tint = if (!isFavorite) Color.Black else Color.Red
            )
        }
    }
}