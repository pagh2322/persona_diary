package com.threesharp.personadiary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Persona::class], version = 1)
abstract class PersonaDatabase: RoomDatabase() {
    abstract fun personaDao(): PersonaDao

    companion object {
        private var INSTANCE: PersonaDatabase? = null

        fun getInstance(context: Context): PersonaDatabase? {
            if (INSTANCE == null) {
                synchronized(PersonaDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, PersonaDatabase::class.java, "persona").fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
    }
}