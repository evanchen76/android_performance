package evan.chen.tutorial.retrofitsample

data class TodosResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)


