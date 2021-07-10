package br.com.vpec.app17_listaprogramadores

import androidx.room.*

@Entity(tableName = "tb_programador")
data class Programador(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    @ColumnInfo(name="Nome")
    val nome: String,
)

