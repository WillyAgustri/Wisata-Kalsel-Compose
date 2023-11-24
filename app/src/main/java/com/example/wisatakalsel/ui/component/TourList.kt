package com.example.wisatakalsel.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wisatakalsel.model.Tour
import io.eyram.iconsax.IconSax

@Composable
fun TourColumn(
    listTour: List<Tour>,
    navigateToDetail: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(bottom = 30.dp)
            .testTag("lazyColumn"),
        contentPadding = PaddingValues(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(listTour, key = { it.id }) {
            Box(
                modifier = Modifier
                    .height(219.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .border(0.5.dp, Color.LightGray)
                    .clickable { navigateToDetail(it.id) }
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    painter = painterResource(id = it.image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
                Surface(
                    modifier = Modifier
                        .height(77.dp)
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.onPrimary)
                        .align(Alignment.BottomStart),
                ) {
                    Column(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = IconSax.Linear.Image),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp),
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = it.name,
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 15.sp),
                                color = MaterialTheme.colorScheme.onBackground,

                                )
                        }
                        Row {
                            Icon(
                                painter = painterResource(id = IconSax.Linear.Location),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp),
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = it.location,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.secondary,

                                )
                        }
                    }
                }
            }
        }
    }
}