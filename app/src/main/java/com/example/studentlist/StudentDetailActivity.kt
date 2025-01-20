package com.example.studentlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentlist.model.Model
import com.example.studentlist.model.Student

class StudentDetailActivity : AppCompatActivity() {
    private var studentUuid: Number = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        studentUuid = intent.getIntExtra("student_uuid", -1)

        if (studentUuid == -1) {
            // Handle the error if the student ID is not passed
            Toast.makeText(this, "Student ID not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Fetch student details
        val student = Model.shared.getStudentByUuid(studentUuid)

        if (student == null) {
            // Handle the error if the student is not found
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Set student details to the views
//        setUserDetails(student)

        findViewById<Button>(R.id.studentDetailEditButton).setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student_uuid", student.uuid)
            startActivity(intent)
        }
    }

    override fun onResume() {
        Log.d("ON-RESUME-TAG", "here")
        Log.d("ON-RESUME-TAG", studentUuid.toString())

        super.onResume()
        val student = Model.shared.getStudentByUuid(studentUuid)

        if(student != null) {
            Log.d("ON-RESUME-TAG", "student is not null")
            Log.d("ON-RESUME-TAG", student.toString())
            setUserDetails(student)
        }

    }

    private fun setUserDetails(student: Student) {
        findViewById<TextView>(R.id.studentDetailName).text = student.name
        findViewById<TextView>(R.id.studentDetailId).text = "ID: ${student.id}"
        findViewById<ImageView>(R.id.studentDetailPicture).setImageResource(student.pictureUrl)
        findViewById<TextView>(R.id.studentDetailPhone).text = "Phone number: ${student.phoneNumber}"
        findViewById<TextView>(R.id.studentDetailAddress).text = "Address: ${student.address}"
        findViewById<CheckBox>(R.id.studentCheck).let {
            it.isChecked = student.isChecked
            it.isClickable = false
        }
    }
}
