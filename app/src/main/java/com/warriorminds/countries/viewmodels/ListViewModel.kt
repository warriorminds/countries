package com.warriorminds.countries.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.warriorminds.countries.models.Country
import com.warriorminds.countries.repositories.CountriesRepository
import com.warriorminds.countries.utils.ResponseCode
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ListViewModel @Inject constructor(private val repository: CountriesRepository): ViewModel(), CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val countries: MutableLiveData<List<Country>> = MutableLiveData()
    val error: MutableLiveData<Boolean> = MutableLiveData()

    fun getCountries() {
        launch {
            val response = withContext(Dispatchers.IO) {
                repository.getCountriesAsync()
            }
            if (response.responseCode == ResponseCode.SUCCESS) {
                countries.value = response.countries
            } else {
                error.value = error.value?.not() ?: true
            }
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}