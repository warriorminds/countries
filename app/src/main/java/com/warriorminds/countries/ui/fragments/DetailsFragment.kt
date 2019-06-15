package com.warriorminds.countries.ui.fragments


import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.squareup.picasso.Picasso
import com.warriorminds.countries.R
import com.warriorminds.countries.models.Country
import com.warriorminds.countries.ui.fragments.CountriesListFragment.Companion.COUNTRY_ARG
import com.warriorminds.countries.utils.FlagSize
import com.warriorminds.countries.utils.getCommaFormattedNumber
import com.warriorminds.countries.utils.getFlagUrl
import com.warriorminds.countries.utils.moreInformation
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_details.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.util.*

class DetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val country = it[COUNTRY_ARG] as Country
            activity!!.title = country.name
            details_continent.text = getString(R.string.details_region, country.region, country.subregion)
            details_capital.text = getString(R.string.details_capital, country.capital)
            details_population.text = getString(R.string.details_population, country.population.getCommaFormattedNumber())

            details_area.text = Html.fromHtml(getString(R.string.details_area, country.area.getCommaFormattedNumber()))
            details_phone_codes.text = getString(R.string.details_phone_codes, country.callingCodes.joinToString(separator = ", "))

            val currentTimes = country.timezones.joinToString(separator = ", ")
            details_time.text = getString(R.string.details_current_times, currentTimes)
            details_extra.text = country.moreInformation()
            Picasso.get().load(country.getFlagUrl(FlagSize.BIG)).into(details_flag)

            if (!country.latlng.isNullOrEmpty()) {
                val bundle = Bundle()
                bundle.putDouble(MapFragment.LATITUDE, country.latlng[0])
                bundle.putDouble(MapFragment.LONGITUDE, country.latlng[1])
                fab_map.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.map_action, bundle))
                fab_map.show()
            } else {
                fab_map.hide()
            }
        }
    }
}
