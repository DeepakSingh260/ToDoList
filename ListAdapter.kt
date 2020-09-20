package com.example.todolist.activity.adapter

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.activity.Task.Task
import com.example.todolist.activity.database.TaskEntity
import com.example.todolist.activity.showtask

class ListAdapter(val context: Context, val taskList: List<TaskEntity>):RecyclerView.Adapter<ListAdapter.ListViewHolder>(),
    android.widget.ListAdapter {

    class ListViewHolder(view:View) : RecyclerView.ViewHolder(view){

        val rlView:RelativeLayout = view.findViewById(R.id.rlcontent)
        val txtTitle:TextView = view.findViewById(R.id.txtsinglerow)
        val imgtitle :ImageView = view.findViewById(R.id.imgsinglerow)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        println(taskList)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_single_row,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return   taskList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val task = taskList[position]
        holder.txtTitle.text = task.TaskTitle
        holder.rlView.setOnClickListener {
            if(task.TaskDescription!=null) {
            val intent = Intent(context , showtask::class.java )
            intent.putExtra("task_title" , task.TaskTitle)
                intent.putExtra("task_id" , task.id)
                intent.putExtra("task_description", task.TaskDescription)
                context.startActivity(intent)
            }else{
                Toast.makeText(context , "error" , Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEnabled(position: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun areAllItemsEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }


}