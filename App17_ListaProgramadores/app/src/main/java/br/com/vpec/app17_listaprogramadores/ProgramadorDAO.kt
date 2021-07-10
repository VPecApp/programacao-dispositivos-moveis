package br.com.vpec.app17_listaprogramadores

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProgramadorDAO {
    @Query("SELECT * FROM tb_programador")
    suspend fun getProgramadores(): List<Programador>

    @Insert
    suspend fun addProgramadores(t: Programador)

    @Delete
    suspend fun deleteProgramador(t: Programador)
}
