package com.example.triqui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.triqui.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtonTicTacToe()
        setupSpinnerIdiomas()
        setupButtonRandomGreet()
        setupButtonCountries()
    }

    private fun setupButtonTicTacToe() {
        binding.buttonTicTacToe.setOnClickListener {
        val intent = Intent(this, TicTacToeActivity::class.java)
        startActivity(intent)
        }
    }

    private fun setupSpinnerIdiomas() {
        val idiomas = resources.getStringArray(R.array.idiomas)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, idiomas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIdiomas.adapter = adapter
    }

    private fun setupButtonRandomGreet() {
        binding.buttonRandomGreet.setOnClickListener {
            val idiomaSeleccionado = binding.spinnerIdiomas.selectedItem.toString()
            val intent = Intent(this, GreetActivity::class.java)
            intent.putExtra("IDIOMA_SELECCIONADO", idiomaSeleccionado)
            startActivity(intent)
        }
    }

    private fun setupButtonCountries() {
        binding.buttonCountries.setOnClickListener {
            val intent = Intent(this, CountriesActivity::class.java)
            startActivity(intent)
        }
    }
}
