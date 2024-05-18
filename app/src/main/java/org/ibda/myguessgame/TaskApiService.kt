package org.ibda.myguessgame

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
interface TaskApiService {
    @GET("/tasks")
    fun getTasks(): Call<List<TaskInfo>>
}