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
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class TaskDetailViewModel : ViewModel() {
    private lateinit var retrofit: Retrofit
    private lateinit var taskApiService: TaskApiService

    val id = MutableLiveData<Int>(0)
    val title = MutableLiveData<String>("")
    val description = MutableLiveData<String>("")
    val category = MutableLiveData<String>("")
    val status = MutableLiveData<String>("")
    val created_time = MutableLiveData<String>("")
    val finished_time = MutableLiveData<String>("")
    val duration = MutableLiveData<String>("")

    val action = MutableLiveData<String>("")
    val normal = MutableLiveData<Boolean>(false)
    val important = MutableLiveData<Boolean>(false)
    val urgent = MutableLiveData<Boolean>(false)

    init{
        this.retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        this.taskApiService = this.retrofit.create(TaskApiService::class.java)
    }

    fun getTask(id: Int?){
        val call = taskApiService.getTask(id)
        call.enqueue(object : Callback<TaskInfo> {
            override fun onFailure(call: Call<TaskInfo>, t: Throwable) {
                Log.e("TaskDetailViewModel", "Failed to get search results", t)
            }

            override fun onResponse(call: Call<TaskInfo>, response: Response<TaskInfo>) {
                if (response.isSuccessful) {
                    val task = response.body()
                    title.value = task?.title
                    description.value = task?.description
                    category.value = task?.category
                    status.value = task?.status
                    created_time.value = task?.created_time
                    finished_time.value = task?.finished_time
                    duration.value = task?.duration

                    changeActionButton(status.value)
                } else {
                    Log.e("TaskDetailViewModel", "Failed to get results by category and status: ${response.errorBody()?.string()}")
                }
            }
        })
    }

    private fun calculateDuration(createdTime: String, finishedTime: String): String {
        val createdDateTime = LocalDate.parse(createdTime)
        val finishedDateTime = LocalDate.parse(finishedTime)
        val daysBetween = ChronoUnit.DAYS.between(createdDateTime, finishedDateTime)
        return "$daysBetween days"
    }

    fun changeStatus(id: Int){
        if(this.status.value == "in progress"){
            this.finished_time.value = LocalDate.now().toString()
            this.duration.value = calculateDuration(this.created_time.value!!, this.finished_time.value!!)
            this.status.value = "done"
            val task = TaskInfo(
                id = this.id.value!!,
                title = this.title.value!!,
                description = this.description.value!!,
                category = this.category.value!!,
                created_time = this.created_time.value!!,
                finished_time = this.finished_time.value!!,
                duration = this.duration.value!!,
                status = this.status.value!!)
            putApi(id, task)
        } else {
            if(this.status.value == "new"){
                this.status.value = "in progress"
                val task = TaskInfo(
                    id = this.id.value!!,
                    title = this.title.value!!,
                    description = this.description.value!!,
                    category = this.category.value!!,
                    created_time = this.created_time.value!!,
                    finished_time = "",
                    duration = "",
                    status = this.status.value!!)
                putApi(id, task)
            } else {
                goToNav()
            }

        }
    }

    fun changeActionButton(status: String?){
        if(status == "new"){
            this.action.value = "Take"
        } else if(status == "in progress"){
            this.action.value = "Done"
        } else if(status == "done"){
            this.action.value = "Back"
        }
    }

    fun putApi(id: Int, task: TaskInfo){
        val call = taskApiService.changeStatus(id, task)
        call.enqueue(object : Callback<TaskInfo> {
            override fun onFailure(call: Call<TaskInfo>, t: Throwable) {
                Log.e("TaskDetailViewModel", "Failed to update task", t)
                goToNav()
            }
            override fun onResponse(call: Call<TaskInfo>, response: Response<TaskInfo>) {
                if (response.isSuccessful) {
                    val updatedTask = response.body()
                    Log.d("TaskDetailViewModel", "Task updated: $updatedTask")
                    goToNav()
                } else {
                    Log.e("TaskDetailViewModel", "Failed to update task: ${response.errorBody()?.string()}")
                    goToNav()
                }
            }
        })
    }

    fun goToNav(){
        if(this.category.value == "normal"){
            this.normal.value = true
        }
        if(this.category.value == "important"){
            this.important.value = true
        }
        if(this.category.value == "urgent"){
            this.urgent.value = true
        }
    }
}