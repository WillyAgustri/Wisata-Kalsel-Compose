package com.example.wisatakalsel.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wisatakalsel.R
import com.example.wisatakalsel.ui.component.CtopAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {
    Scaffold(
        topBar = {
            CtopAppBar(titleText = "About Me")
        }

    ) { innerpadding ->
        Modifier.padding(innerpadding)
        AboutInformation()
    }
}

@Composable
fun AboutInformation() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center


    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.offset(y = (-80).dp)
        ) {
            Image(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.willyagustridjabar),
                contentDescription = null,
                contentScale = ContentScale.Crop,

                )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Willy Agustri Djabar",
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 15.sp)
            )
            Text(
                text = "willyofficial082@gmail.com",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
            )
            Text(
                text = "A2023012",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
            )

        }
    }
}


@Preview
@Composable
fun AboutScreenPreview() {
    AboutScreen()
}
