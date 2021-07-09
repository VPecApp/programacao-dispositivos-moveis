package com.joseffe.aula07

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import java.net.URI

class NotaAdapter(var listaNota: MutableList<Nota>, var context: Context):RecyclerView.Adapter<NotaAdapter.ItemViewHolder>() {

    // Responsável por encontrar os componentes dentro do layout (item_nota) e indicar que o
    //  layout será replicado na Recycler View
    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imgFoto: ImageView = view.findViewById(R.id.imgFoto)
        val txtNome: TextView = view.findViewById(R.id.txtNome)
        val txtCategoria: TextView = view.findViewById(R.id.txtCategoria)
        val txtEstagio: TextView = view.findViewById(R.id.txtEstagio)
    }

    // Responsável por inflar (fazer aparecer) o layout por exemplo na activity ou fragment ou dialog (tudo que extends View Group)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota, parent, false)
        return ItemViewHolder(view)
    }

    // Vincula os componentes do layout (item_nota) a um dado/propriedade/atributo da lista
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        listaNota[position].foto?.let{
            holder.imgFoto.setImageBitmap(it)
        }
        holder.txtNome.text = listaNota[position].nome
        holder.txtCategoria.text = listaNota[position].categoria.nome
        holder.txtEstagio.text = listaNota[position].estagio.nome

        // Implementando o "click" do CardView para retornar um Toast como mensagem
        holder.itemView.setOnClickListener(){
            var texto = if (listaNota[position].urgente) {
                "Nota urgente!"
            }
            else
            {
                "Nota comum"
            }

            Toast.makeText(context, texto, Toast.LENGTH_SHORT).show()
        }
    }

    // Indica quantos registros/itens a lista possui
    override fun getItemCount(): Int {
        return listaNota.size
    }
}
