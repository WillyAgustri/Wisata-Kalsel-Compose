package com.example.wisatakalsel.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wisatakalsel.injection.Injection
import com.example.wisatakalsel.model.Tour
import com.example.wisatakalsel.ui.common.UiState
import com.example.wisatakalsel.ui.component.CtopAppBar
import com.example.wisatakalsel.ui.component.EmptyList
import com.example.wisatakalsel.ui.component.SearchBar
import com.example.wisatakalsel.ui.component.TourColumn
import com.example.wisatakalsel.ui.viewmodel.HomeViewModel
import com.example.wisatakalsel.ui.viewmodel.ViewModelFactory

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,


    ) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.search(query)
            }

            is UiState.Success -> {
                HomeBody(
                    query = query,
                    onQueryChange = viewModel::search,
                    listTour = uiState.data,
                    onFavoriteIconClicked = { id, newState ->
                        viewModel.updatePlayer(id, newState)
                    },
                    navigateToDetail = navigateToDetail
                )
            }

            is UiState.Error -> {}
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBody(
    listTour: List<Tour>,
    query: String,
    onQueryChange: (String) -> Unit,
    onFavoriteIconClicked: (id: Int, newState: Boolean) -> Unit,
    navigateToDetail: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            CtopAppBar(titleText = "Tour Kalimantan Selatan")
        }

    ) { innerPadding ->
        Column(
            Modifier.padding(innerPadding)
        ) {
            SearchBar(query = query, onQueryChange = onQueryChange)
            if (listTour.isNotEmpty()) {
                TourColumn(
                    listTour = listTour,
                    navigateToDetail = navigateToDetail
                )
            } else {
                EmptyList(
                    Warning = "Wisata Tidak Ditemukan",
                    modifier = Modifier
                        .testTag("emptyList")
                )
            }
        }
    }
}



