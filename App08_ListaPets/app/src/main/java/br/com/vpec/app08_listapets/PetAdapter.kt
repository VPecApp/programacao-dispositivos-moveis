package  br.com.vpec.app08_listapets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PetAdapter(var listaPet: MutableList<Pet>):RecyclerView.Adapter<PetAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val foto: ImageView = view.findViewById(R.id.foto)
        val nome: TextView = view.findViewById(R.id.nome)
        val tipo: TextView = view.findViewById(R.id.tipo)
        val genero: TextView = view.findViewById(R.id.genero)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pet, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        listaPet[position].foto?.let{
            holder.foto.setImageDrawable(it)
        }
        holder.nome.text = listaPet[position].nome
        holder.tipo.text = listaPet[position].tipo.tipo
        holder.genero.text = listaPet[position].genero.genero

    }

    override fun getItemCount(): Int {
        return listaPet.size
    }
}