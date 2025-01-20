package com.example.studentlist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentlist.model.Model
import com.example.studentlist.model.Student

class MainActivity : AppCompatActivity() {
    private var students: List<Student>? = null
    private var adapter: StudentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        students = Model.shared.students

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentAdapter(students) { student ->
            // Handle row click, navigate to the details screen
            val intent = Intent(this, StudentDetailActivity::class.java)
            intent.putExtra("student_uuid", student?.uuid)
            startActivity(intent)
        }

        findViewById<Button>(R.id.mainActivityAddButton).setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter?.setStudents(Model.shared.students)
        adapter?.notifyDataSetChanged()
    }
}
