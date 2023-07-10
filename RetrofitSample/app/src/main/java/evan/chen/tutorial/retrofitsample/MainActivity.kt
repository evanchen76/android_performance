package evan.chen.tutorial.retrofitsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import evan.chen.tutorial.retrofitsample.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.getTodo.setOnClickListener {
            callAPI()
        }
    }

    private fun callAPI() {
        val apiService = AppClientManager.client.create(ApiService::class.java)
        apiService.getTodo(1).enqueue(object : Callback<TodosResponse> {
            override fun onResponse(call: Call<TodosResponse>, response: Response<TodosResponse>) {
                println("responseBody:${response.body()}")
                response.body()?.let { todo ->
                    val result =
                        "id:${todo.id}\nuserId:${todo.userId}\ntitle:${todo.title}\ncompleted:${todo.completed}"
                    binding.result.text = result
                }

            }

            override fun onFailure(call: Call<TodosResponse>, t: Throwable) {
                println("onFailure:$t")
            }
        })
    }

}