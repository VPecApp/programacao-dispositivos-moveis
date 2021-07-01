package br.com.vpec.app04_alcool_gasolina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity () {

    lateinit var resultado: TextView
    lateinit var botaoCalcular: Button
    var precoGasolina: Double? = null
    var precoEtanol: Double? = null

    override fun onCreate ( savedInstanceState : Bundle? ) {
        super .onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultado = findViewById(R.id.txtResultado)
        botaoCalcular = findViewById(R.id.btnCalcular)
        botaoCalcular.setOnClickListener {
            precoGasolina = findViewById<EditText>(R.id.edtGasolina).text.toString().toDoubleOrNull()
            precoEtanol = findViewById<EditText>(R.id.edtEtanol).text.toString().toDoubleOrNull()
            calcularMelhorOpcao(precoEtanol, precoGasolina)
        }
    }

    fun calcularMelhorOpcao (alcool: Double?, gasolina: Double?){
        var result: Double
        var resultadoText = ""

        if(alcool != null && gasolina != null){
            result = alcool.div(gasolina)

            if(result < 0.7)
                resultadoText = "Melhor utilizar Alcóol"

            if(result >= 0.7)
                resultadoText = "Melhor utilizar Gasolina"


        }else{
            resultadoText = "Opção incorreta!"
        }

        resultado.text = resultadoText
    }
}
