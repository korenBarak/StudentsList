package com.example.studentlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val students = listOf(
            Student("1", "Koren Barak", R.drawable.user_image, false, "0506762051", "Rishon"),
            Student("2", "Gal Inbar", R.drawable.user_image, false, "052849374", "Rishon"),
            Student("3", "Inbal Barak", R.drawable.user_image, false, "0546829384", "Tel Aviv")
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = StudentAdapter(students) { student ->
            // Handle row click, navigate to the details screen
            val intent = Intent(this, StudentDetailActivity::class.java)
            intent.putExtra("student_id", student.id)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }
}
