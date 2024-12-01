package com.rakaliaksei.littlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rakaliaksei.littlelemon.AppDatabase
import com.rakaliaksei.littlelemon.MenuItemRoom
import com.rakaliaksei.littlelemon.Profile
import com.rakaliaksei.littlelemon.R
import com.rakaliaksei.littlelemon.ui.theme.Color2
import com.rakaliaksei.littlelemon.ui.theme.Color3
import com.rakaliaksei.littlelemon.ui.theme.Color8
import coil.compose.rememberAsyncImagePainter

@Composable
fun Home(navController: NavController? = null, database: AppDatabase) {
    val databaseMenuItems = database.menuItemDao().getAll().observeAsState(emptyList())
    var menuItems = databaseMenuItems.value.sortedBy { it.title }

    val searchPhrase = remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color8)
    ) {
        Row(
            modifier = Modifier
                .height(96.dp)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(96.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .clickable { navController?.navigate(Profile.route) }
                    .fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color2)
                .padding(16.dp)
        ) {
            Text(
                text = "Little Lemon",
                color = Color3,
                fontSize = 48.sp,
                fontFamily = FontFamily.Serif,
            )
            Row() {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                ) {
                    Text(
                        text = "Chicago",
                        color = Color8,
                        fontSize = 36.sp,
                        fontFamily = FontFamily.Serif,
                    )
                    Text(
                        text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                        color = Color8,
                        fontSize = 16.sp,
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.hero),
                    contentDescription = "Example of dish",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                )
            }
            OutlinedTextField(
                value = searchPhrase.value,
                onValueChange = { searchPhrase.value = it },
                label = { Text(text = "Enter search phrase") },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
        if (searchPhrase.value.isNotEmpty()) {
            menuItems = menuItems
                .filter {
                    it.title.contains(searchPhrase.value, ignoreCase = true)
                }
        }
        MenuItemsList(menuItems)
    }
}

@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 16.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Column() {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .wrapContentHeight()
                        ) {
                            Text(
                                menuItem.title,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Text(
                                menuItem.description,
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                            )
                            Text(
                                text = "$%.2f".format(menuItem.price),
                                modifier = Modifier
                                    .padding(top = 5.dp),
                            )
                        }
                        Column() {
                            Image(
                                painter = rememberAsyncImagePainter(menuItem.image),
                                contentDescription = "Image from URL",
                                modifier = Modifier
                                    .height(72.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }
                    }
                }
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                )
            }
        )
    }
}