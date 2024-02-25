package com.example.triqui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.triqui.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayCountryDetails()
    }

    private fun displayCountryDetails() {
        val countryName = intent.getStringExtra("COUNTRY_NAME")
        val countryCapital = intent.getStringExtra("COUNTRY_CAPITAL")
        val countryIntName = intent.getStringExtra("COUNTRY_NAME_INT")
        val countrySigla = intent.getStringExtra("INITIAL")
        val countryFlagUrl = intent.getStringExtra("COUNTRY_FLAG_URL")

        // Mostrar los datos del país en los TextViews
        binding.textViewCountryName.text = "Nombre del País: $countryName"
        binding.textViewCountryCapital.text = "Capital: $countryCapital"
        binding.textViewCountryIntName.text = "Nombre Internacional: $countryIntName"
        binding.textViewCountrySigla.text = "Sigla: $countrySigla"

        // Cargar la bandera del país en el ImageView
        Glide.with(this)
            .load(countryFlagUrl)
            .into(binding.imageViewCountryFlag)
    }
}
