package br.com.vpec.app17_listaprogramadores

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Programador::class), version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun programadorDao():ProgramadorDAO

}
