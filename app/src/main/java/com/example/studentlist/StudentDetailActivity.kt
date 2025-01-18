package com.example.studentlist

import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StudentDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        val studentId = intent.getStringExtra("student_id")
        if (studentId == null) {
            // Handle the error if the student ID is not passed
            Toast.makeText(this, "Student ID not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Fetch student details
        val student = fetchStudentDetails(studentId)
        if (student == null) {
            // Handle the error if the student is not found
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Set student details to the views
        findViewById<TextView>(R.id.studentDetailName).text = student.name
        findViewById<TextView>(R.id.studentDetailId).text = "ID: ${student.id}"
        findViewById<ImageView>(R.id.studentDetailPicture).setImageResource(student.pictureUrl)
        findViewById<TextView>(R.id.studentDetailPhone).text = "Phone number: ${student.phoneNumber}"
        findViewById<TextView>(R.id.studentDetailAddress).text = "Address: ${student.address}"
        findViewById<CheckBox>(R.id.studentCheck).isActivated = student.isChecked
    }

    // Mock function to simulate fetching student details
    private fun fetchStudentDetails(studentId: String): Student? {
        // Replace with real database or API call
        val students = listOf(
            Student("1", "Koren Barak", R.drawable.user_image, false, "0506762051", "Rishon"),
            Student("2", "Gal Inbar", R.drawable.user_image, false, "052849374", "Rishon"),
            Student("3", "Inbal Barak", R.drawable.user_image, false, "0546829384", "Tel Aviv")
        )
        return students.find { it.id == studentId }
    }
}
