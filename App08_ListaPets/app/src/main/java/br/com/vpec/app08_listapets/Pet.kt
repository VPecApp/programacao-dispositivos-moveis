package  br.com.vpec.app08_listapets

import android.graphics.drawable.Drawable

data class Pet(
    var foto: Drawable?=null,
    var nome: String,
    var tipo: TipoPet,
    var genero: GeneroPet
)