package com.example.studentlist.model

import android.util.Log
import com.example.studentlist.R

class Model private constructor() {
    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    init {
        val initialStudents : List<Student> = listOf(
            Student(
                id = "1",
                name = "Koren Barak",
                pictureUrl = R.drawable.user_image,
                isChecked = false,
                phoneNumber = "0506762051",
                address = "Rishon"
            ),
            Student(
                id = "2",
                name = "Gal Inbar",
                pictureUrl = R.drawable.user_image,
                isChecked = false,
                phoneNumber = "052849374",
                address = "Rishon"
            ),
            Student(
                id = "3",
                name = "Inbal Barak",
                pictureUrl = R.drawable.user_image,
                isChecked = false,
                phoneNumber = "0546829384",
                address = "Tel Aviv"
            )
        )

        students.addAll(initialStudents)
    }

    fun getStudentByUuid(uuid: Number) : Student? {
        return students.find { it.uuid == uuid }
    }

    fun addStudent(newStudent: Student) {
        students.add(newStudent)
    }

    fun editStudent(studentStaticId: Number, newStudentInfo: Student) {
        val studentIndex = students.indexOfFirst{it.uuid == studentStaticId}

        if (studentIndex != -1) {
            students[studentIndex] = newStudentInfo
        }
    }

    fun removeStudent(studentStaticId: Number) {
        val studentIndex = students.indexOfFirst{it.uuid == studentStaticId}
        students.removeAt(studentIndex)
    }
}