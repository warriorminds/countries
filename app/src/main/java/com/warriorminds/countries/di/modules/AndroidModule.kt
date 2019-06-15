package com.warriorminds.countries.di.modules

import com.warriorminds.countries.ui.activities.MainActivity
import com.warriorminds.countries.ui.fragments.CountriesListFragment
import com.warriorminds.countries.ui.fragments.DetailsFragment
import com.warriorminds.countries.ui.fragments.MapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun countriesListFragment(): CountriesListFragment

    @ContributesAndroidInjector
    abstract fun detailsFragment(): DetailsFragment

    @ContributesAndroidInjector
    abstract fun mapFragment(): MapFragment
}