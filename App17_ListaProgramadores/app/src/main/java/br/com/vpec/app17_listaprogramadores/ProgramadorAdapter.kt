package br.com.vpec.app17_listaprogramadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProgramadorAdapter(var listaProgramador: List<Programador>, var listener: ProgramadorAdapterListener):RecyclerView.Adapter<ProgramadorAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtProgramador: TextView = view.findViewById(R.id.txtProgramador)
        val btnExcluir: ImageButton = view.findViewById(R.id.btnExcluir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_programador, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.txtProgramador.text = listaProgramador[position].nome

        holder.btnExcluir.setOnClickListener(){
            listener.excluirProgramador(listaProgramador[position])
        }
    }

    override fun getItemCount(): Int {
        return listaProgramador.size
    }

    fun refreshListProgramador(listaAtualizada: List<Programador>){
        listaProgramador = listaAtualizada
        notifyDataSetChanged()
    }
}
