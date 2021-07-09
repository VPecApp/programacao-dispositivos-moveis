package com.joseffe.aula10tarefas

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Tarefa::class), version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun tarefaDao():TarefaDAO

    // Implementar aqui todos os DAOs que o App tiver daqui pra baixo...
}
