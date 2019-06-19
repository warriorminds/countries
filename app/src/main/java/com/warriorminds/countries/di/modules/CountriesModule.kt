package com.warriorminds.countries.di.modules

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.warriorminds.countries.db.CountriesDatabase
import com.warriorminds.countries.maps.GoogleMapProvider
import com.warriorminds.countries.maps.MapProvider
import com.warriorminds.countries.network.CountriesService
import com.warriorminds.countries.network.monitor.NetworkMonitor
import com.warriorminds.countries.network.monitor.NetworkMonitorImpl
import com.warriorminds.countries.repositories.CountriesRepository
import com.warriorminds.countries.repositories.CountriesRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class CountriesModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesContext(): Context = context

    @Singleton
    @Provides
    fun providesOkHttp() = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .cache(Cache(File(context.cacheDir, "http-cache"), 25 * 1024 * 1024))
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .baseUrl("https://restcountries.eu/")
        .build()

    @Singleton
    @Provides
    fun provideCountriesService(retrofit: Retrofit): CountriesService = retrofit.create(CountriesService::class.java)

    @Singleton
    @Provides
    fun provideCountriesRepository(repositoryImpl: CountriesRepositoryImpl): CountriesRepository = repositoryImpl

    @Singleton
    @Provides
    fun provideMapProvider(googleMapProvider: GoogleMapProvider): MapProvider = googleMapProvider

    @Singleton
    @Provides
    fun providesDatabase(context: Context) : CountriesDatabase =
        Room.databaseBuilder(context, CountriesDatabase::class.java, "countries.db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providesnetworkMonitor(networkMonitor: NetworkMonitorImpl): NetworkMonitor = networkMonitor
}