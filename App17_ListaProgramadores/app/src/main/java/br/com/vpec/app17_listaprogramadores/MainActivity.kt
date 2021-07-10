package br.com.vpec.app17_listaprogramadores

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), ProgramadorAdapterListener {

    lateinit var programadorAdapter: ProgramadorAdapter
    lateinit var edtProgramador: EditText
    lateinit var btnIncluir: ImageButton
    lateinit var preferenciasProgramador: SharedPreferences
    lateinit var rv: RecyclerView

    var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferenciasProgramador = getSharedPreferences("programadorPreferences", Context.MODE_PRIVATE)

        rv = findViewById<RecyclerView>(R.id.rvProgramadores)

        btnIncluir = findViewById(R.id.btnIncluir)
        edtProgramador = findViewById(R.id.editarProgramador)

        btnIncluir.setOnClickListener() {
            if (edtProgramador.text.toString().isNotEmpty()) {
                adicionarProgramador(edtProgramador.text.toString())
                edtProgramador.text.clear()

                val editor = preferenciasProgramador.edit()
                editor.remove("NOME")
                editor.commit()
            } else {
                edtProgramador.error = "Insira um texto válido!"
            }
        }
    }

    fun adicionarProgramador(nomeProgramador: String) {
        CoroutineScope(Dispatchers.IO).launch {
            db = DatabaseBuilder.getAppDatabase(this@MainActivity)

            db?.programadorDao()?.addProgramadores(Programador(nome = nomeProgramador))

            val programadorDAO = db?.programadorDao()

            val resposta = programadorDAO?.getProgramadores()

            withContext(Dispatchers.Main) {
                resposta?.let {
                    programadorAdapter.refreshListProgramador(resposta)
                }
            }
        }
    }

    fun recuperarLista(){
        CoroutineScope(Dispatchers.IO).launch {
            db = DatabaseBuilder.getAppDatabase(this@MainActivity)

            val programadorDAO = db?.programadorDao()

            val resposta = programadorDAO?.getProgramadores()

            withContext(Dispatchers.Main){
                resposta?.let{
                    programadorAdapter = ProgramadorAdapter(it, this@MainActivity)

                    rv.adapter = programadorAdapter

                    rv.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()

        if (edtProgramador.text.toString().isNotEmpty()){

            val editor = preferenciasProgramador.edit()

            editor.putString("Nome", edtProgramador.text.toString())

            editor.commit()
        }
    }

    override fun onResume() {
        super.onResume()

        edtProgramador.setText(preferenciasProgramador.getString("Nome", null))
    }

    override fun onStart() {
        super.onStart()

        recuperarLista()
    }

    override fun excluirProgramador(programador: Programador) {
        CoroutineScope(Dispatchers.IO).launch {
            db = DatabaseBuilder.getAppDatabase(this@MainActivity)

            db?.programadorDao()?.deleteProgramador(programador)

            val programadorDAO = db?.programadorDao()

            val resposta = programadorDAO?.getProgramadores()

            withContext(Dispatchers.Main) {
                resposta?.let {
                    programadorAdapter.refreshListProgramador(resposta)

                    Toast.makeText(this@MainActivity, "Programador excluído", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}