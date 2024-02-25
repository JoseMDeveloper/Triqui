
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import classes.Country

class CountryAdapter(context: Context, countries: List<Country>) :
    ArrayAdapter<Country>(context, 0, countries) {

    // Sobreescribimos el método getView que se encarga de crear la vista de cada elemento en la lista.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView // Almacenamos la vista del elemento en la lista.

        // Miramos si ya esta inflada o no.
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
        }
        // Obtenemos el objeto Country en la posición de la lista.
        val country = getItem(position)

        // Encontramos el TextView dentro de la vista inflada y establecemos el nombre del país.
        val textView = itemView!!.findViewById<TextView>(android.R.id.text1)
        textView.text = country?.nombrePais

        return itemView
    }
}
