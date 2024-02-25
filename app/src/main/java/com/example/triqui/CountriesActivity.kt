package com.example.triqui
import CountryAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.triqui.databinding.ActivityCountriesBinding
import org.json.JSONObject
import classes.Country


class CountriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountriesBinding
    private lateinit var countries: MutableList<Country>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadCountries()

        val adapter = CountryAdapter(this, countries)
        binding.listViewCountries.adapter = adapter
        binding.listViewCountries.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedCountry = countries[position]
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("COUNTRY_NAME", selectedCountry.nombrePais)
                intent.putExtra("COUNTRY_CAPITAL", selectedCountry.capital)
                intent.putExtra("COUNTRY_NAME_INT", selectedCountry.nombrePaisInt)
                intent.putExtra("INITIAL", selectedCountry.sigla)
                intent.putExtra("COUNTRY_FLAG_URL", selectedCountry.banderaUrl)
                startActivity(intent)
            }
    }

    private fun loadCountries() {
        countries = mutableListOf()
        try {
            val inputStream = resources.openRawResource(R.raw.paises)
            val json_string = inputStream.bufferedReader().use { it.readText() }
            val json = JSONObject(json_string)
            val paisesJsonArray = json.getJSONArray("paises")

            for (i in 0 until paisesJsonArray.length()) {
                val jsonObject = paisesJsonArray.getJSONObject(i)
                val capital = jsonObject.getString("capital")
                val nombrePais = jsonObject.getString("nombre_pais")
                val nombrePaisInt = jsonObject.getString("nombre_pais_int")
                val sigla = jsonObject.getString("sigla")
                val banderaUrl = jsonObject.getString("bandera")
                val country = Country(capital, nombrePais, nombrePaisInt, sigla, banderaUrl)
                countries.add(country)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error al cargar los países", Toast.LENGTH_SHORT).show()
        }
    }
}

