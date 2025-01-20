package com.example.studentlist

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentlist.model.Model
import com.example.studentlist.model.Student

class AddStudentActivity : AppCompatActivity() {

    private var nameEditText: EditText? = null
    private var idEditText: EditText? = null
    private var phoneEditText: EditText? = null
    private var addressEditText: EditText? = null
    private var checkBox: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameEditText = findViewById(R.id.addStudentNameEditText)
        idEditText = findViewById(R.id.addStudentIdEditText)
        phoneEditText = findViewById(R.id.addStudentPhoneEditText)
        addressEditText = findViewById(R.id.addStudentAddressEditText)
        checkBox = findViewById(R.id.addStudentCheckBox)

        findViewById<Button>(R.id.addStudentCancelButton).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.addStudentSaveButton).setOnClickListener {
            val id : String = idEditText?.text.toString()
            val name : String = nameEditText?.text.toString()
            val isChecked : Boolean = checkBox?.isChecked ?: false
            val phoneNumber : String = phoneEditText?.text.toString()
            val address : String = addressEditText?.text.toString()

            if(id.isEmpty() || name.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "One or more properties are missing", Toast.LENGTH_SHORT).show()
            } else {
                val newStudent = Student(
                    id = id,
                    name = name,
                    pictureUrl = R.drawable.user_image,
                    isChecked = isChecked,
                    phoneNumber = phoneNumber,
                    address = address
                )

                Model.shared.addStudent(newStudent)
                finish()
            }
        }
    }
}