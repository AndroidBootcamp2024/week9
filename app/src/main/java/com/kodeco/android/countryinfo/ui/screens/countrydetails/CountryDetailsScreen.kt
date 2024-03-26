package com.kodeco.android.countryinfo.ui.screens.countrydetails

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.repositories.CountryRepository
import com.kodeco.android.countryinfo.sample.sampleCountries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetailsScreen(
    viewModel: CountryDetailsViewModel,
    onNavigateUp: () -> Unit,
) {
    val country by viewModel.country.collectAsState()
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item { Text(text = country.commonName, style = TextStyle(fontSize= 30.sp) )}
        item { Text(text = "Capital: ${country.mainCapital}") }
        item { Text(text = "Population: ${country.population}") }
        item { Text(text = "Area: ${country.area}") }
        item {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(country.flagUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Flag",
                contentScale = ContentScale.Fit,
                modifier = Modifier.border(1.dp, color = MaterialTheme.colorScheme.primary)
            )
        }
    }
}

@Preview
@Composable
fun CountryDetailsScreenPreview() {
    CountryDetailsScreen(
        viewModel = CountryDetailsViewModel(
                repository = object : CountryRepository {
                    override fun fetchCountries(): Flow<List<Country>> = flow {
                        emit(sampleCountries)
                    }

                    override fun getCountry(index: Int): Country? {
                        return sampleCountries.getOrNull(index)
                    }
                },
            countryId= 0
        ) ,
        onNavigateUp = {},
    )
}
