package com.warriorminds.countries.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.warriorminds.countries.R
import com.warriorminds.countries.adapters.CountriesAdapter
import com.warriorminds.countries.viewmodels.ListViewModel
import com.warriorminds.countries.viewmodels.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_countries_list.*
import javax.inject.Inject

class CountriesListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var countriesAdapter: CountriesAdapter
    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[ListViewModel::class.java]
        viewModel.countries.observe(this, Observer {
            list_progress.visibility = View.GONE
            countriesAdapter.setCountries(it)
        })

        viewModel.error.observe(this, Observer {
            list_progress.visibility = View.GONE
            Snackbar.make(countries_list, getString(R.string.countries_error), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.retry)) {
                    list_progress.visibility = View.VISIBLE
                    viewModel.getCountries()
                }
                .show()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_countries_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(countries_list) {
            val manager = LinearLayoutManager(context)
            adapter = countriesAdapter
            layoutManager = manager
            addItemDecoration(DividerItemDecoration(context, manager.orientation))
        }

        viewModel.getCountries()
    }

}
