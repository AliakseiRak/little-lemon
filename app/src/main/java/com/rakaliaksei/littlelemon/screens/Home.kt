package com.rakaliaksei.littlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rakaliaksei.littlelemon.Profile
import com.rakaliaksei.littlelemon.R
import com.rakaliaksei.littlelemon.ui.theme.Color8

@Preview(showBackground = true)
@Composable
fun Home(navController: NavController? = null) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color8)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp)
                .background(Color8)
                .padding(16.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .clickable { navController?.navigate(Profile.route) }
                .fillMaxWidth()
                .fillMaxHeight()
                .height(96.dp)
                .background(Color8)
                .padding(16.dp)
        )
    }
}