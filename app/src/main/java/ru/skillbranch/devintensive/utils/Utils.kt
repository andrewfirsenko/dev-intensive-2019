package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        return when (fullName) {
            "", " " -> null to null
            else -> {
                val parts = fullName?.split(" ")

                val firstName = parts?.getOrNull(0)
                val lastName = parts?.getOrNull(1)

                //                Pair(firstName, lastName)
                firstName to lastName
            }
        }
    }
}