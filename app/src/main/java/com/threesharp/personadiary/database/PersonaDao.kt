package com.threesharp.personadiary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonaDao {
    @Query("SELECT * FROM persona")
    fun getAll(): LiveData<List<Persona>>
    @Query("SELECT * FROM persona WHERE type IN (:type)")
    fun get(type: Int): LiveData<List<Persona>>
    @Query("SELECT * FROM persona WHERE type IN (:type) AND sex IN (:sex)")
    fun get(type: Int, sex: Int): LiveData<List<Persona>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(persona: Persona)
    @Query("DELETE FROM Persona WHERE id IN (:id)")
    fun delete(id: Int)
}