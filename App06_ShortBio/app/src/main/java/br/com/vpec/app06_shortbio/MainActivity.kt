package br.com.vpec.app06_shortbio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var sobreButton : Button
    private lateinit var formacaoButton : Button
    private lateinit var experienciaButton : Button
    private lateinit var objetivoButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sobreButton = findViewById(R.id.sobre_button)
        formacaoButton = findViewById(R.id.formacao_button)
        experienciaButton = findViewById(R.id.experiencia_button)
        objetivoButton = findViewById(R.id.objetivo_button)

        sobreButton.setOnClickListener{
            startActivity(Intent(this@MainActivity, SobreActivity::class.java))
        }

        formacaoButton.setOnClickListener{
            startActivity(Intent(this@MainActivity, FormacaoActivity::class.java))
        }

        experienciaButton.setOnClickListener{
            startActivity(Intent(this@MainActivity, ExperienciaActivity::class.java))
        }


        objetivoButton.setOnClickListener{
            startActivity(Intent(this@MainActivity, ObjetivoActivity::class.java))
        }
    }
}