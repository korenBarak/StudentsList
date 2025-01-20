package com.example.studentlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentlist.model.Student

class StudentAdapter(
    private var students: List<Student>?,
    private val onRowClicked: (Student?) -> Unit
    ) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

        inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val picture: ImageView = view.findViewById(R.id.studentPicture)
            val name: TextView = view.findViewById(R.id.studentName)
            val id: TextView = view.findViewById(R.id.studentId)
            val checkBox: CheckBox = view.findViewById(R.id.studentCheck)

            fun bind(student: Student?) {
                student?.pictureUrl?.let {
                    picture.setImageResource(it)
                }

                name.text = student?.name
                id.text = student?.id
                checkBox.isChecked = student?.isChecked ?: false

                // Checkbox Listener
                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    student?.isChecked = isChecked
                }

                // Row Click Listener
                itemView.setOnClickListener {
                    onRowClicked(student)
                }
            }
        }

        fun setStudents(students:List<Student>?) {
            this.students = students
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.student_list_item, parent, false)
            return StudentViewHolder(view)
        }

        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
            holder.bind(students?.get(position))
        }

        override fun getItemCount(): Int = students?.size ?: 0
    }