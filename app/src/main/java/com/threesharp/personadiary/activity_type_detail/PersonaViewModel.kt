package com.threesharp.personadiary.activity_type_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.threesharp.personadiary.database.Persona
import com.threesharp.personadiary.database.PersonaRepository

class PersonaViewModel(application: Application): AndroidViewModel(application) {
    private val repository = PersonaRepository(application)
    fun getAll(): LiveData<List<Persona>> {
        return repository.getAll()
    }
    fun getAll(type: Int): LiveData<List<Persona>> {
        return repository.get(type)
    }
    fun getMale(type: Int): LiveData<List<Persona>> {
        return repository.get(type, 0)
    }
    fun getFemale(type: Int): LiveData<List<Persona>> {
        return repository.get(type, 1)
    }
    fun insert(persona: Persona) {
        repository.insert(persona)
    }
    fun delete(id: Int) {
        repository.delete(id)
    }
}