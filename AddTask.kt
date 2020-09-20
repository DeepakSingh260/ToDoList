package com.example.todolist.activity

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.room.Room
import com.example.todolist.R
import com.example.todolist.activity.database.TaskDatabase
import com.example.todolist.activity.database.TaskEntity
import com.squareup.picasso.Picasso

class AddTask : AppCompatActivity() {

    lateinit var taskTitle : EditText

    lateinit var taskDescription: EditText

    lateinit var taskImage : ImageView
    lateinit var taskButton: Button

    class DBAsync(val context: Context,val taskEntity: TaskEntity): AsyncTask<Void,Void,Boolean>(){

        val db = Room.databaseBuilder(context , TaskDatabase::class.java , "task-db").build()
        override fun doInBackground(vararg params: Void?): Boolean {

                    db.taskDao().insertTask(taskEntity)
                    db.close()
               return true
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)


        taskTitle = findViewById<EditText>(R.id.inputTitless)
        taskDescription = findViewById<EditText>(R.id.inputDescription)
        taskImage = findViewById(R.id.imgimg)
        taskButton = findViewById(R.id.addtaskbutton)

        val title =  taskTitle.text
        println("title ----------------------------------------------------------------------------${title.toString()}")
        val description =  taskDescription.text
        println("descriptiom ----------------------------------------------------------------------------${description.toString()}")









        taskButton.setOnClickListener {
            if (title.toString() != "" && description.toString() != "") {
                val taskEntity=TaskEntity (
                     title.toString(),description.toString()
                )
                DBAsync(applicationContext, taskEntity).execute()
                println("Successfull")
                startActivity(Intent(this@AddTask , MainActivity::class.java))
                finish()

            }else{
                Toast.makeText(this@AddTask , "Fill Both Title and Description", Toast.LENGTH_SHORT).show()

            }
        }



    }
}
