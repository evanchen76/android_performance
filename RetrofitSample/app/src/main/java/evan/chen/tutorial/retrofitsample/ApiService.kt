package evan.chen.tutorial.retrofitsample

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("todos/{id}")
    fun getTodo(@Path("id") id: Int): Call<TodosResponse>
}