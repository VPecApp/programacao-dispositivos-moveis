package br.com.vpec.listaprogramadores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.vpec.listaprogramadores.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rvUsuarios)

        val lista = mutableListOf<Usuario>(
            Usuario(nome="Samanta", email="samanta@vpec.com", stack= Stack.FULLSTACK, senioridade= Senioridade.SENIOR, foto=resources.getDrawable(R.drawable.samanta)),
            Usuario(nome="Marcos", email="marcos@vpec.com", stack= Stack.FULLSTACK, senioridade= Senioridade.JUNIOR, foto=resources.getDrawable(R.drawable.marcos)),
            Usuario(nome="Lúbia", email="lubia@vpec.com", stack= Stack.FRONTEND, senioridade= Senioridade.JUNIOR, foto=resources.getDrawable(R.drawable.lubia)),
            Usuario(nome="Júlia", email="julia@vpec.com", stack= Stack.FRONTEND, senioridade= Senioridade.JUNIOR, foto=resources.getDrawable(R.drawable.julia   ))
        )

        rv.adapter = UsuarioAdapter(lista)

        rv.layoutManager = LinearLayoutManager(this)
    }
}