package com.threesharp.personadiary.constants

import com.threesharp.personadiary.R

class Personality(type : String, color : Int, sColor : Int) {
    private var type: String = ""
    private var color: Int = -1
    private var sColor: Int = -1
    init {
        this.type = type
        this.color = color
        this.sColor = sColor
    }
    fun getType(): String {
        return this.type
    }
    fun getColor(): Int {
        return this.color
    }
    fun getSColor(): Int {
        return this.sColor
    }
}

object PersonalityList {
    private val typeList = listOf(
        Personality("ISTJ-A/T", R.color.ISTJ, R.color.sISTJ),
        Personality("ISFJ-A/T", R.color.ISFJ, R.color.sISFJ),
        Personality("INFJ-A/T", R.color.INFJ, R.color.sINFJ)
    )
    fun get(pos : Int): Personality {
        return typeList[pos]
    }
}
