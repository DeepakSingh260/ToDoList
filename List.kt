package com.example.todolist.activity.fragment

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

import com.example.todolist.R
import com.example.todolist.activity.AddTask
import com.example.todolist.activity.Task.Task
import com.example.todolist.activity.database.TaskDatabase
import com.example.todolist.activity.database.TaskEntity
import kotlin.collections.List

/**
 * A simple [Fragment] subclass.
 */
class List : Fragment() {

    lateinit var recyclerList: RecyclerView
    lateinit var layoutManager: LinearLayoutManager

    lateinit var recyclerAdapter : ListAdapter

    var taskList = listOf<TaskEntity>()

    lateinit var bookId :String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view= inflater.inflate(R.layout.fragment_list, container, false)
        setHasOptionsMenu(true)

        recyclerList = view.findViewById(R.id.recyclerList)


        layoutManager = LinearLayoutManager(activity)

        taskList = retriveasync(activity as Context).execute().get()


        recyclerAdapter = com.example.todolist.activity.adapter.ListAdapter(activity as Context,taskList)

        recyclerList.layoutManager=layoutManager
        recyclerList.adapter = recyclerAdapter as com.example.todolist.activity.adapter.ListAdapter

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.addtask,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId
        if(id == R.id.add_task){
            val intent = Intent(activity as Context , AddTask::class.java)

            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }


    class retriveasync(val context: Context) : AsyncTask< Void,Void,List<TaskEntity> >(){
        override fun doInBackground(vararg params: Void?): List<TaskEntity> {

            val db = Room.databaseBuilder(context , TaskDatabase::class.java,"task-db").build()
            return db.taskDao().getalltasks()
        }

    }
}

