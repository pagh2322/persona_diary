package com.threesharp.personadiary.constants

enum class PersonalityType {
    ISTJ, ISFJ, INFJ, INTJ, ISTP, ISFP, INFP, INTP, ESTP, ESFP, ENFP, ENTP, ESTJ, ESFJ, ENFJ, ENTJ, NONE;
}
fun binaryToPT(binary: Int): PersonalityType {
    val type: PersonalityType = when (binary) {
        0 -> PersonalityType.ISTJ
        10 -> PersonalityType.ISFJ
        110 -> PersonalityType.INFJ
        100 -> PersonalityType.INTJ
        1 -> PersonalityType.ISTP
        11 -> PersonalityType.ISFP
        111 -> PersonalityType.INFP
        101 -> PersonalityType.INTP
        1001 -> PersonalityType.ESTP
        1011 -> PersonalityType.ESFP
        1111 -> PersonalityType.ENFP
        1101 -> PersonalityType.ENTP
        1000 -> PersonalityType.ESTJ
        1010 -> PersonalityType.ESFJ
        1110 -> PersonalityType.ENFJ
        1100 -> PersonalityType.ENTJ
        else -> PersonalityType.NONE
    }
    return type
}