package com.warriorminds.countries.di.components

import com.warriorminds.countries.CountriesApp
import com.warriorminds.countries.di.modules.AndroidModule
import com.warriorminds.countries.di.modules.CountriesModule
import com.warriorminds.countries.di.modules.ViewModelsModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AndroidModule::class, CountriesModule::class, ViewModelsModule::class])
interface AppComponent {

    fun inject(app: CountriesApp)
}