package com.example.studentlist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentlist.model.Model
import com.example.studentlist.model.Student

class EditStudentActivity : AppCompatActivity() {
    var studentUuid: Number = -1

    var nameEditText: EditText? = null
    var idEditText: EditText? = null
    var phoneEditText: EditText? = null
    var addressEditText: EditText? = null
    var checkBox: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        studentUuid = intent.getIntExtra("student_uuid", -1)

        if (studentUuid == -1) {
            Toast.makeText(this, "Student ID not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val student = Model.shared.getStudentByUuid(studentUuid)

        if (student == null) {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        nameEditText = findViewById(R.id.nameEditText)
        idEditText = findViewById(R.id.idEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        addressEditText = findViewById(R.id.addressEditText)
        checkBox = findViewById(R.id.editStudentCheckBox)

        findViewById<ImageView>(R.id.studentEditPicture).setImageResource(student.pictureUrl)

        nameEditText?.setText(student.name)
        idEditText?.setText(student.id)
        phoneEditText?.setText(student.phoneNumber)
        addressEditText?.setText(student.address)
        checkBox?.isChecked = student.isChecked


        findViewById<Button>(R.id.editStudentCancelButton).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.editStudentSaveButton).setOnClickListener {
            val newStudent = Student(
                uuid = studentUuid,
                id = idEditText?.text.toString(),
                name = nameEditText?.text.toString(),
                pictureUrl = R.drawable.user_image,
                isChecked = checkBox?.isChecked ?: false,
                phoneNumber = phoneEditText?.text.toString(),
                address = addressEditText?.text.toString(),
            )

            Model.shared.editStudent(studentUuid, newStudent)

            finish()
        }

        findViewById<Button>(R.id.editStudentDeleteButton).setOnClickListener {
            finish()
        }


        findViewById<Button>(R.id.editStudentDeleteButton).setOnClickListener {
            Model.shared.removeStudent(studentUuid)
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}