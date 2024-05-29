package org.ibda.myguessgame

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.POST
import retrofit2.http.PUT

interface TaskApiService {
    @GET("/tasks")
    fun getTasks(): Call<List<TaskInfo>>

    @GET("/tasks/{id}")
    fun getTask(@Path("id") id: Int?): Call<TaskInfo>

    @GET("/tasks")
    fun getTasksByCategoryAndStatus(
        @Query("category") category: String,
        @Query("status") status: String):
            Call<List<TaskInfo>>

    @POST("/tasks")
    fun addTask(@Body taskInfo: TaskInfo): Call<TaskInfo>

    @PUT("/task_data/{id}")
    fun changeStatus(@Path("id") id: Int, @Body taskInfo: TaskInfo): Call<TaskInfo>

}