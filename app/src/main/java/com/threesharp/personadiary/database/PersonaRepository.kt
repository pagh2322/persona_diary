package com.threesharp.personadiary.database

import android.app.Application
import androidx.lifecycle.LiveData

class PersonaRepository(application: Application) {
    private val personaDatabase = PersonaDatabase.getInstance(application)!!
    private val personaDao: PersonaDao = personaDatabase.personaDao()
    private val personaList: LiveData<List<Persona>> = personaDao.getAll()

    fun getAll(): LiveData<List<Persona>> {
        return personaList
    }
    fun get(type: Int): LiveData<List<Persona>> {
        return personaDao.get(type)
    }
    fun get(type: Int, sex: Int): LiveData<List<Persona>> {
        return personaDao.get(type, sex)
    }
    fun insert(persona: Persona) {
        try {
            val thread = Thread {
                personaDao.insert(persona)
            }
            thread.start()
        } catch (e: Exception) { }
    }
    fun delete(id: Int) {
        try {
            val thread = Thread {
                personaDao.delete(id)
            }
            thread.start()
        } catch (e: Exception) { }
    }
}