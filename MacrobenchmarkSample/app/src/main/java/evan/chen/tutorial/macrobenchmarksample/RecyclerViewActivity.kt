package evan.chen.tutorial.macrobenchmarksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import evan.chen.tutorial.macrobenchmarksample.MainAdapter
import evan.chen.tutorial.macrobenchmarksample.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    private val binding by lazy { ActivityRecyclerViewBinding.inflate(layoutInflater) }

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val names = listOf(
            "Evan", "Kevin", "Irene", "Joe", "Jack", "Alex", "Bill", "Edison", "Jackson", "Steve",
            "Bob", "Daniel", "David", "Emily", "Emma", "Grace", "Sophia", "Oliver", "Liam", "Benjamin",
            "Charlotte", "Mia", "Henry", "William", "James", "Michael", "Sophie", "Lucas", "Alexander", "Thomas",
            "Olivia", "Ava", "Isabella", "Sophia", "Charlotte", "Amelia", "Mia", "Harper", "Evelyn", "Abigail",
            "Emily", "Elizabeth", "Mila", "Ella", "Avery", "Sofia", "Camila", "Aria", "Scarlett", "Victoria",
            "Madison", "Luna", "Grace", "Chloe", "Penelope", "Layla", "Riley", "Zoey", "Nora", "Lily",
            "Eleanor", "Hannah", "Lillian", "Addison", "Aubrey", "Ellie", "Stella", "Natalie", "Zoe", "Leah",
            "Hazel", "Violet", "Aurora", "Savannah", "Audrey", "Brooklyn", "Bella", "Claire", "Skylar", "Lucy",
            "Paisley", "Everly", "Anna", "Caroline", "Nova", "Genesis", "Emilia", "Kennedy", "Samantha", "Maya",
            "Willow", "Kinsley", "Naomi", "Aaliyah", "Elena", "Sarah", "Ariana", "Allison", "Gabriella", "Alice")

        adapter = MainAdapter(names)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
    }
}