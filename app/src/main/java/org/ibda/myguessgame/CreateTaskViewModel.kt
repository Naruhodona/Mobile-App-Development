package org.ibda.myguessgame

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CreateTaskViewModel : ViewModel() {
    private lateinit var retrofit : Retrofit
    private lateinit var taskApiService: TaskApiService

    val category = MutableLiveData<String>("")
    val title = MutableLiveData<String>("")
    val description = MutableLiveData<String>("")

    val move = MutableLiveData<Boolean>(false)

    init{
        this.retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        this.taskApiService = this.retrofit.create(TaskApiService::class.java)
    }

    fun addtask(){
        val created_time: LocalDate = LocalDate.now()
        val newTask = TaskInfo(
            id = 0,
            title = this.title.value!!,
            description = this.description.value!!,
            category = this.category.value!!,
            created_time = created_time.toString(),
            finished_time = "",
            duration = "",
            status = "new")
        val call = taskApiService.addTask(newTask)
        call.enqueue(object : Callback<TaskInfo> {
            override fun onFailure(call: Call<TaskInfo>, t: Throwable) {
                // Handle failure
                Log.e("CreateTaskViewModel", "Failed to create task", t)
            }

            override fun onResponse(call: Call<TaskInfo>, response: Response<TaskInfo>) {
                if (response.isSuccessful) {
                    // Handle success
                    val createdTask = response.body()
                    Log.d("CreateTaskViewModel", "Task created: $createdTask")
                    backToHome()

                } else {
                    // Handle failure
                    Log.e("CreateTaskViewModel", "Failed to create task: ${response.errorBody()?.string()}")
                    backToHome()
                }
            }
        })
    }
    fun backToHome(){
        this.move.value = true
    }
}