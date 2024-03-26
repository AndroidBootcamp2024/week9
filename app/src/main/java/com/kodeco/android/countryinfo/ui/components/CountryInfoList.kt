package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.repositories.CountryRepositoryImpl
import com.kodeco.android.countryinfo.sample.sampleCountries
import com.kodeco.android.countryinfo.ui.screens.countrydetails.CountryDetailsScreen
import com.kodeco.android.countryinfo.ui.screens.countrydetails.CountryDetailsViewModel
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoViewModel

@Composable
fun CountryInfoList(
    countries: List<Country>,
    onCountryRowTap : (Int) -> Unit,
    onRefresh: () -> Unit,
) {
    var selectedCountry: Country? by remember { mutableStateOf(null) }
    var tapCounter by remember { mutableIntStateOf(0) }
    var backCounter by remember { mutableIntStateOf(0) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Button(
                onClick = onRefresh,
            ) {
                Text(text = "Refresh")
            }

        }

        selectedCountry?.let { country ->
            val id = countries.indexOf (country)
            onCountryRowTap(id)
        } ?: run {
            LazyColumn {
                items(countries) { country ->
                    CountryInfoRow(country) {
                        selectedCountry = country
                        tapCounter++
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CountryInfoListPreview() {
    CountryInfoList(
        countries = sampleCountries,
        onRefresh = {},
        onCountryRowTap = {},
    )
}
