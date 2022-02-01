package com.threesharp.personadiary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.threesharp.personadiary.constants.PersonalityType

@Entity(tableName = "persona")
data class Persona(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "type")
    var type: Int,
    @ColumnInfo(name = "sex")
    var sex: Int
    ) {
    constructor(): this(null, "", PersonalityType.NONE.ordinal, 0)
}