package com.warriorminds.countries.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.warriorminds.countries.viewmodels.ListViewModel
import com.warriorminds.countries.viewmodels.ViewModelFactory
import com.warriorminds.countries.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(ListViewModel::class)
    abstract fun providesListViewModel(listViewModel: ListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}