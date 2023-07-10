package evan.chen.tutorial.arch.roomcoroutinesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import evan.chen.tutorial.arch.roomcoroutinesample.databinding.ActivityAccountBinding
import evan.chen.tutorial.arch.roomcoroutinesample.utils.DateUtils

class AccountActivity : AppCompatActivity() {

    private lateinit var viewModel: AccountViewModel

    private lateinit var binding: ActivityAccountBinding

    private lateinit var adapter: AccountAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccountBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        //本日
        viewModel.today.observe(this, Observer {
            binding.today.text = DateUtils()
                .dateToString(it)
        })

        //本日記錄
        viewModel.todayRecords.observe(this, Observer {
            adapter = AccountAdapter(viewModel, it)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = adapter
        })

        //本日收入
        viewModel.todaySaving.observe(this, Observer {
            binding.todaySaving.text = "本日收入：$it"
        })

        //本日支出
        viewModel.todaySpending.observe(this, Observer {
            binding.todaySpending.text = "本日支出：$it"
        })

        //開啟記錄明細
        viewModel.openItemEvent.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                val intent = RecordEditActivity.getIntent(this, it)
                startActivity(intent)
            }
        })

        //新增支出
        binding.addSpending.setOnClickListener {
            val intent = RecordAddActivity.getIntent(this, "支出")
            startActivity(intent)
        }

        //新增存入
        binding.addSaving.setOnClickListener {
            val intent = RecordAddActivity.getIntent(this, "收入")
            startActivity(intent)
        }

        //開啟統計頁面
        binding.stat.setOnClickListener {
            val intent = Intent(this, StatisticsActivity::class.java)
            startActivity(intent)
        }

        //下一天
        binding.nextDay.setOnClickListener {
            viewModel.nextDay()
        }

        //前一天
        binding.lastDay.setOnClickListener {
            viewModel.lastDay()
        }

    }
}