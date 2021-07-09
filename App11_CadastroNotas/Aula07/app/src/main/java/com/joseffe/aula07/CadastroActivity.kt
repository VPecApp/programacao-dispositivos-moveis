package com.joseffe.aula07
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var categoria: Categoria
    lateinit var estagio: Estagio
    lateinit var spnEstagio: Spinner
    lateinit var edtNome: EditText
    lateinit var edtEmail: EditText
    lateinit var btnSalvar: Button
    lateinit var imvFoto: ImageView
    var fotoTirada: Bitmap?=null
    lateinit var swtUrgente: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        edtNome = findViewById(R.id.edtNome)
        edtEmail = findViewById(R.id.edtEmail)
        spnEstagio = findViewById(R.id.spnEstagio)
        btnSalvar = findViewById(R.id.btnSalvar)
        imvFoto = findViewById(R.id.imvFoto)
        swtUrgente = findViewById(R.id.swtUrgente)

        // Intent para chamar a Câmera
        imvFoto.setOnClickListener(){
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (intent.resolveActivity(packageManager) != null)
                startActivityForResult(intent, RC_CAMERA)
        }

        // Aqui estamos implementando o "Adapter" do Spinner (Combo), consumindo o array no arquivo strings.xml
        ArrayAdapter.createFromResource(this, R.array.opcoes_spinner, android.R.layout.simple_spinner_item).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnEstagio.adapter = arrayAdapter
        }

        spnEstagio.onItemSelectedListener = this

        btnSalvar.setOnClickListener(){
            val nota = Nota(fotoTirada, edtNome.text.toString(), edtEmail.text.toString(), categoria, estagio, swtUrgente.isChecked)
            MainActivity.lista.add(nota)
            finish()
        }
    }

    fun OnCategoriaClick(view: View){
        if (view is RadioButton){
            var checked = view.isChecked

            when (view.id){
                R.id.rdbCasa -> { if (checked) {categoria = Categoria.CASA}}
                R.id.rdbFaculdade -> { if (checked) {categoria = Categoria.FACULDADE}}
                R.id.rdbTrabalho -> { if (checked) {categoria = Categoria.TRABALHO}}
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p2){
            0 -> {estagio = Estagio.PLANEJADO}
            1 -> {estagio = Estagio.INICIADO}
            2 -> {estagio = Estagio.CONCLUIDO}
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((resultCode == RESULT_OK) && (requestCode == RC_CAMERA)){
            fotoTirada = data?.extras?.get("data") as Bitmap
            imvFoto.setImageBitmap(fotoTirada)
        }
    }

    companion object{
        const val RC_CAMERA  = 12345
        const val RC_GALERIA = 67890
    }
}}