package com.example.triqui
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.triqui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    class Casilla(var estado: Int, val imageView: ImageView)

    private lateinit var casillas: Array<Array<Casilla>>
    private var jugadorActual: Int = 1  // 1 para jugador X, 2 para jugador O

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inicializarCasillas()
        configurarCasillas()

        //Para repetir el juego
        binding.jugadorUno.setBackgroundResource(R.drawable.casilla_seleccionada)
        binding.jugadorDos.setBackgroundResource(R.drawable.casilla)
        binding.botonRepetir.setOnClickListener()
        {
            jugadorActual=2
            reiniciarJuego()
            configurarCasillas()
        }

    }

    private fun inicializarCasillas() {
        casillas = Array(3) { fila ->
            Array(3) { columna ->
                Casilla(0, obtenerReferenciaImageView(fila * 3 + columna))
            }
        }
    }

    private fun obtenerReferenciaImageView(index: Int): ImageView {
        return when (index) {
            0 -> binding.casilla1
            1 -> binding.casilla2
            2 -> binding.casilla3
            3 -> binding.casilla4
            4 -> binding.casilla5
            5 -> binding.casilla6
            6 -> binding.casilla7
            7 -> binding.casilla8
            8 -> binding.casilla9
            else -> throw IllegalArgumentException("Casilla no definida para índice $index")
        }
    }

    private fun configurarCasillas() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                asignarClickListener(i, j)
            }
        }
    }

    private fun asignarClickListener(fila: Int, columna: Int) {
        val casilla = casillas[fila][columna].imageView

        casilla.setOnClickListener {
            if (casillas[fila][columna].estado == 0) { // Verificar si la casilla está vacía
                casillas[fila][columna].estado = jugadorActual
                actualizarImagenCasilla(casillas[fila][columna])

                val ganador = verificarGanador()
                if (ganador != 0) {
                    mostrarGanadorEnToast(ganador)
                    bloquearTablero()

                } else {
                    turnoJugador()
                    jugadorActual = 3 - jugadorActual
                }
            }
        }
    }

    private fun verificarGanador(): Int {
        // Verificar filas
        for (i in 0 until 3) {
            if (casillas[i][0].estado != 0 &&
                casillas[i][0].estado == casillas[i][1].estado &&
                casillas[i][0].estado == casillas[i][2].estado) {

                resaltarCasillaGanadora(i, 0)
                resaltarCasillaGanadora(i, 1)
                resaltarCasillaGanadora(i, 2)
                return casillas[i][0].estado
            }
        }
        // Verificar columnas
        for (j in 0 until 3) {
            if (casillas[0][j].estado != 0 &&
                casillas[0][j].estado == casillas[1][j].estado &&
                casillas[0][j].estado == casillas[2][j].estado) {

                resaltarCasillaGanadora(0, j)
                resaltarCasillaGanadora(1, j)
                resaltarCasillaGanadora(2, j)
                return casillas[0][j].estado
            }
        }
        // Verificar diagonales
        if (casillas[0][0].estado != 0 &&
            casillas[0][0].estado == casillas[1][1].estado &&
            casillas[0][0].estado == casillas[2][2].estado) {

            resaltarCasillaGanadora(0, 0)
            resaltarCasillaGanadora(1, 1)
            resaltarCasillaGanadora(2, 2)
            return casillas[0][0].estado
        }
        if (casillas[0][2].estado != 0 &&
            casillas[0][2].estado == casillas[1][1].estado &&
            casillas[0][2].estado == casillas[2][0].estado) {

            resaltarCasillaGanadora(0, 2)
            resaltarCasillaGanadora(1, 1)
            resaltarCasillaGanadora(2, 0)
            return casillas[0][2].estado
        }
        return 0 // Retorna 0 si no hay ganador
    }

    private fun mostrarGanadorEnToast(ganador: Int) {
        val mensaje = if (ganador == 1) "Jugador 1 gana!" else "Jugador 2 gana!"
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    private fun bloquearTablero() {
        for (fila in casillas) {
            for (casilla in fila) {
                casilla.imageView.setOnClickListener(null)
            }
        }
    }

    private fun resaltarCasillaGanadora(fila: Int, columna: Int) {
        casillas[fila][columna].imageView.setBackgroundResource(R.drawable.casilla_ganadora)
    }

    private fun reiniciarJuego() {
        for (fila in casillas) {
            for (casilla in fila) {
                casilla.estado = 0
                actualizarImagenCasilla(casilla)
                reiniciarTablero(casilla)
                turnoJugador()
            }
        }
        jugadorActual = 1
    }

    private fun actualizarImagenCasilla(casilla: Casilla) {
        when (casilla.estado) {
            1 -> casilla.imageView.setImageResource(R.drawable.icono_x_triqui)
            2 -> casilla.imageView.setImageResource(R.drawable.icono_o_triqui)
            0 -> casilla.imageView.setImageResource(R.drawable.trasparente)
        }
    }

    private fun reiniciarTablero(casilla: Casilla) {
        casilla.imageView.setBackgroundResource(R.drawable.casilla)
    }

    private fun turnoJugador() {
        if (jugadorActual == 1) {
            binding.jugadorUno.setBackgroundResource(R.drawable.casilla)
            binding.jugadorDos.setBackgroundResource(R.drawable.casilla_seleccionada)
        } else {
            binding.jugadorUno.setBackgroundResource(R.drawable.casilla_seleccionada)
            binding.jugadorDos.setBackgroundResource(R.drawable.casilla)

        }
    }
}
