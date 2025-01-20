package com.example.studentlist.model

data class Student(
        val uuid: Number = generateId(),
        val id: String,
        val name: String,
        val pictureUrl: Int,
        var isChecked: Boolean,
        val phoneNumber: String,
        val address: String,
) {
        companion object {
                private var counter = 0

                private fun generateId() : Number {
                        counter++
                        return counter
                }
        }
}