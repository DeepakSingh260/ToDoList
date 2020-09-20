package com.example.todolist.activity

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.example.todolist.R
import com.example.todolist.activity.database.TaskDatabase
import com.example.todolist.activity.database.TaskEntity
import kotlinx.android.synthetic.main.activity_showtask.*

class showtask : AppCompatActivity() {
    var taskTitle: String? = ""
    var taskDescription: String? = ""
    lateinit var showtasttitle: TextView
    lateinit var showtaskdescription: TextView
    lateinit var showtaskbutton: Button
    var taskid: Int? = 0


    class DBAsyc(val context: Context, val id: String) : AsyncTask<Void, Void, TaskEntity>() {

        val db = Room.databaseBuilder(context, TaskDatabase::class.java, "task-db").build()
        override fun doInBackground(vararg params: Void?): TaskEntity {

            return db.taskDao().getTaskById(id)



        }

    }
    class ADBAsyc(val context: Context,val taskEntity: TaskEntity) : AsyncTask<Void, Void, Boolean>() {

        val db = Room.databaseBuilder(context, TaskDatabase::class.java, "task-db").build()
        override fun doInBackground(vararg params: Void?): Boolean {


           db.taskDao().deleteTask(taskEntity)
            return true

        }

    }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_showtask)

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            if (intent != null) {
                taskTitle = intent.getStringExtra("task_title")
                taskDescription = intent.getStringExtra("task_description")
                taskid = intent.getIntExtra("task_id", -1)
            } else {
                Toast.makeText(this@showtask, "error occured", Toast.LENGTH_SHORT).show()
                finish()
            }
            if (taskId == -1) {
                Toast.makeText(this@showtask, "id occured", Toast.LENGTH_SHORT).show()
                finish()
            }
            showtasttitle = findViewById(R.id.showtaskTitle)
            showtaskdescription = findViewById(R.id.showtaskDescription)
            showtaskbutton = findViewById(R.id.showtaskButton)

            showtasttitle.text = taskTitle
            showtaskdescription.text = taskDescription

            showtaskbutton.setOnClickListener {
                println("id=========================${taskid}}")
                if (taskid != null) {
                    val string:String = taskid.toString()
                    val async = DBAsyc(applicationContext, string).execute().get()
                    println(async)
                    ADBAsyc(applicationContext , async).execute()

                }
                println("------------------------------------------")

               val intent = Intent(this@showtask, MainActivity::class.java)
                intent.putExtra("task",true)
                startActivity(intent)
                finish()

            }


        }


}
