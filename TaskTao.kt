package com.example.todolist.activity.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskTao {
    @Insert
    fun insertTask(taskEntity: TaskEntity)


    @Delete
    fun deleteTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM Tasks")
    fun getalltasks():List<TaskEntity>


    @Query("SELECT * FROM Tasks WHERE id=:taskId")
    fun getTaskById(taskId:String):TaskEntity







}