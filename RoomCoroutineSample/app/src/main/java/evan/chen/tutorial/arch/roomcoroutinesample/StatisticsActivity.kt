package evan.chen.tutorial.arch.roomcoroutinesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import evan.chen.tutorial.arch.roomcoroutinesample.databinding.ActivityStatisticsBinding

class StatisticsActivity : AppCompatActivity() {

    private lateinit var viewModel: StatisticsViewModel

    private lateinit var binding: ActivityStatisticsBinding

    private lateinit var adapter: StatisticsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStatisticsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(StatisticsViewModel::class.java)

        viewModel.accountStatistics.observe(this, Observer {
            adapter = StatisticsAdapter(it)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = adapter
        })

        viewModel.month.observe(this, Observer {
            binding.month.text = "${it.year} ${it.month}"
        })

        binding.nextMonth.setOnClickListener {
            viewModel.nextMonth()
        }

        binding.lastMonth.setOnClickListener {
            viewModel.lastMonth()
        }

    }
}