package com.warriorminds.countries

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.warriorminds.countries.di.components.DaggerAppComponent
import com.warriorminds.countries.di.modules.CountriesModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class CountriesApp : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerAppComponent.builder()
            .countriesModule(CountriesModule(this))
            .build()
        appComponent.inject(this)
    }

    override fun activityInjector() = dispatchingActivityInjector

    override fun supportFragmentInjector() = dispatchingFragmentInjector
}