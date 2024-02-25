package com.example.triqui
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Obtener los datos del país del intent
        val countryName = intent.getStringExtra("COUNTRY_NAME")
        val countryCapital = intent.getStringExtra("COUNTRY_CAPITAL")
        val countryIntName = intent.getStringExtra("COUNTRY_NAME_INT")
        val countrySigla = intent.getStringExtra("INITIAL")
        val countryFlagUrl = intent.getStringExtra("COUNTRY_FLAG_URL")


        // Mostrar los datos del país en los TextViews
        findViewById<TextView>(R.id.textViewCountryName).text = "Nombre del País: $countryName"
        findViewById<TextView>(R.id.textViewCountryCapital).text = "Capital: $countryCapital"
        findViewById<TextView>(R.id.textViewCountryIntName).text = "Nombre Internacional: $countryIntName"
        findViewById<TextView>(R.id.textViewCountrySigla).text = "Sigla: $countrySigla"

        // Cargar la bandera del país en el ImageView
        val imageViewCountryFlag = findViewById<ImageView>(R.id.imageViewCountryFlag)
        Glide.with(this)
            .load(countryFlagUrl)
            .into(imageViewCountryFlag)

    }
}