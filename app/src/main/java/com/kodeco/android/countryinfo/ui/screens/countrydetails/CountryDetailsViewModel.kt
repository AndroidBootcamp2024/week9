package com.kodeco.android.countryinfo.ui.screens.countrydetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.models.CountryFlags
import com.kodeco.android.countryinfo.models.CountryName
import com.kodeco.android.countryinfo.repositories.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class CountryDetailsViewModel(
    private val repository: CountryRepository,
    private val countryId: Int,
): ViewModel() {
    private val emptyCountry = Country(CountryName(""),listOf(),0,0.0f,CountryFlags("",""))
    private  val _country = MutableStateFlow(emptyCountry)
    val country: StateFlow<Country> = _country.asStateFlow()

    init {
        this._country.value = repository.getCountry(countryId) ?: emptyCountry
    }

    class CountryDetailsViewModelFactory( private val repository: CountryRepository, private val countryId: Int) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            CountryDetailsViewModel(repository, countryId) as T
    }
}