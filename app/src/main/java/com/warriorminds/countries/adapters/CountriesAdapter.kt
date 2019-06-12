package com.warriorminds.countries.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.warriorminds.countries.R
import com.warriorminds.countries.models.Country
import com.warriorminds.countries.utils.FlagSize
import com.warriorminds.countries.utils.getFlagUrl
import kotlinx.android.synthetic.main.country_list_item.view.*
import java.lang.Exception
import javax.inject.Inject

class CountriesAdapter @Inject constructor(): RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>(), Filterable {

    private var countries = listOf<Country>()
    private var filteredCountries = listOf<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountriesViewHolder(parent)

    override fun getItemCount() = filteredCountries.size

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val country = filteredCountries[position]
        holder.itemView.country_name.text = country.name
        Picasso.get().load(country.getFlagUrl(FlagSize.SMALL)).into(holder.itemView.flag_image)
    }

    fun setCountries(countries: List<Country>) {
        this.countries = countries
        this.filteredCountries = countries
        notifyDataSetChanged()
    }

    override fun getFilter() = object : Filter() {
        override fun performFiltering(query: CharSequence): FilterResults {
            val queryString = query.toString()
            if (!queryString.isNullOrEmpty()) {
                val filterList = mutableListOf<Country>()
                for (country in countries) {
                    if (country.name.toLowerCase().startsWith(queryString.toLowerCase())) {
                        filterList.add(country)
                    }
                }
                filteredCountries = filterList
            } else {
                filteredCountries = countries
            }
            val filterResults = FilterResults()
            filterResults.values = filteredCountries
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filteredCountries = results?.values as List<Country>
            notifyDataSetChanged()
        }
    }

    inner class CountriesViewHolder(viewGroup: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.country_list_item, viewGroup, false)
    )
}