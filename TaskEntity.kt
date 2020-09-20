package com.example.todolist.activity.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Tasks")
data class TaskEntity (

    @ColumnInfo(name = "task_title")  val TaskTitle:String,
    @ColumnInfo(name = "task_description") val TaskDescription:String,
    @PrimaryKey (autoGenerate = true) val id:Int = 0


)