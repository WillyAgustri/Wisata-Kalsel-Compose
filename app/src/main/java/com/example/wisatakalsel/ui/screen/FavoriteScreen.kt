package com.example.wisatakalsel.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wisatakalsel.injection.Injection
import com.example.wisatakalsel.model.Tour
import com.example.wisatakalsel.ui.common.UiState
import com.example.wisatakalsel.ui.component.CtopAppBar
import com.example.wisatakalsel.ui.component.EmptyList
import com.example.wisatakalsel.ui.component.TourColumn
import com.example.wisatakalsel.ui.viewmodel.FavoriteViewModel
import com.example.wisatakalsel.ui.viewmodel.ViewModelFactory


@Composable
fun FavoriteScreen(
    navigateToDetail: (Int) -> Unit,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFavoriteTour()
            }

            is UiState.Success -> {
                Column {
                    CtopAppBar(titleText = "Favorite")
                    FavoriteInformation(
                        listTour = uiState.data,
                        navigateToDetail = navigateToDetail,
                        onFavoriteIconClicked = { id, newState ->
                            viewModel.updateTour(id, newState)
                        }
                    )
                }

            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavoriteInformation(
    listTour: List<Tour>,
    navigateToDetail: (Int) -> Unit,
    onFavoriteIconClicked: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        if (listTour.isNotEmpty()) {
            TourColumn(
                listTour = listTour,
                navigateToDetail = navigateToDetail
            )
        } else {
            EmptyList(
                Warning = "Tidak ada Daftar Favorite"
            )
        }
    }
}