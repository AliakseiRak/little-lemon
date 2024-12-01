package com.rakaliaksei.littlelemon.screens

import android.content.SharedPreferences
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rakaliaksei.littlelemon.Onboarding
import com.rakaliaksei.littlelemon.R
import com.rakaliaksei.littlelemon.ui.theme.Color3
import com.rakaliaksei.littlelemon.ui.theme.Color4
import com.rakaliaksei.littlelemon.ui.theme.Color7
import com.rakaliaksei.littlelemon.ui.theme.Color8

@Preview(showBackground = true)
@Composable
fun Profile(navController: NavController? = null, sharedPref: SharedPreferences? = null) {

//    with(sharedPref.edit()) {
//        putString("FirstName", firstName)
//        putString("LastName", lastName)
//        putString("Email", email)
//        putBoolean("LoggedIn", true)
//        apply()
//    }

    val firstName = sharedPref?.getString("FirstName", "") ?: "First Name"
    val lastName = sharedPref?.getString("LastName", "") ?: "Last Name"
    val email = sharedPref?.getString("Email", "") ?: "email"

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
        Text(
            text = "Personal information",
            textAlign = TextAlign.Start,
            style = TextStyle(
                fontSize = 16.sp,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color8)
                .padding(top = 8.dp)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        )
        OutlinedTextField(
            value = firstName,
            onValueChange = { },
            label = { Text("First name") },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = { },
            label = { Text("Last name") },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { },
            label = { Text("email") },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                sharedPref?.edit()?.clear()?.apply()
                navController?.navigate(Onboarding.route)
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color3,
                contentColor = Color4,
            ),
            border = BorderStroke(1.dp, Color4),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(text = "Log out", color = Color7)
        }
    }
}