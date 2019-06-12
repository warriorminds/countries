package com.warriorminds.countries.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
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

class CountriesAdapter @Inject constructor(): RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    private var countries = listOf<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountriesViewHolder(parent)

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val country = countries[position]
        holder.itemView.country_name.text = country.name
        Picasso.get().load(country.getFlagUrl(FlagSize.SMALL)).into(holder.itemView.flag_image, object : Callback {
            override fun onSuccess() {

            }

            override fun onError(e: Exception?) {

            }

        })
    }

    fun setCountries(countries: List<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }

    inner class CountriesViewHolder(viewGroup: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.country_list_item, viewGroup, false)
    )
}