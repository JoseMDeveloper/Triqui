package com.example.triqui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.triqui.databinding.ActivityGreetBinding

class GreetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGreetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGreetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el idioma seleccionado del Intent


        when (intent.getStringExtra("IDIOMA_SELECCIONADO")) {
            "Español" -> mostrarSaludo("Hola")
            "English" -> mostrarSaludo("Hello")
            "Italiano" -> mostrarSaludo("Ciao")
            "日本語" -> mostrarSaludo("こんにちは")
            "Deutsch" -> mostrarSaludo("Hallo")

            else -> mostrarSaludo("Hello")
        }
    }

    private fun mostrarSaludo(saludo: String) {

        binding.textViewSaludo.text = saludo
    }
}
