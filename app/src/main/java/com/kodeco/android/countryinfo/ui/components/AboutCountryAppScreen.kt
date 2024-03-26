package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutCountryApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "This app is developed by Kodeco for the purpose of learning about Android Development.",
            style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Version 1.0",
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal)
        )
    }
}

@Composable
@Preview
fun AboutCountryAppPreview() {
    AboutCountryApp()
}