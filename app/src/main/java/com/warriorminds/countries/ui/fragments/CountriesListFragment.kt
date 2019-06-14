package com.warriorminds.countries.ui.fragments


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.warriorminds.countries.R
import com.warriorminds.countries.adapters.CountriesAdapter
import com.warriorminds.countries.models.Country
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

    companion object {
        const val COUNTRY_ARG = "country_arg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

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

    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.app_name)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
        manageSearchView(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when (id) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun manageSearchView(menu: Menu) {
        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                countriesAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                countriesAdapter.filter.filter(query)
                return false
            }
        })
    }
}
