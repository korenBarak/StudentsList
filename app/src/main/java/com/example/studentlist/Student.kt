package com.example.studentlist

data class Student(
        val id: String,
        val name: String,
        val pictureUrl: Int,
        var isChecked: Boolean,
        val phoneNumber: String,
        val address: String,
)