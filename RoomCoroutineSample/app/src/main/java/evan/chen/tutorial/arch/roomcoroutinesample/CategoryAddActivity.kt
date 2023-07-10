package evan.chen.tutorial.arch.roomcoroutinesample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import evan.chen.tutorial.arch.roomcoroutinesample.data.AccountCategory
import evan.chen.tutorial.arch.roomcoroutinesample.databinding.ActivityCategoryAddBinding
import kotlinx.android.synthetic.main.activity_category_add.*

class CategoryAddActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 1
        const val RESULT_CODE = 1

        const val TYPE = "type"

        fun getIntent(context: Context, type: String): Intent {
            val intent = Intent(context, CategoryAddActivity::class.java)
            intent.putExtra(TYPE, type)

            return intent
        }
    }

    lateinit var type: String

    private lateinit var addViewModel: CategoryAddViewModel

    private lateinit var binding: ActivityCategoryAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryAddBinding.inflate(layoutInflater)

        setContentView(binding.root)

        addViewModel = ViewModelProvider(this).get(CategoryAddViewModel::class.java)

        type = intent.getStringExtra(RecordAddActivity.TYPE) ?: ""

        binding.send.setOnClickListener {
            val accountCategory = AccountCategory(0, type, categoryName.text.toString())
            addViewModel.insert(accountCategory)

            setResult(RESULT_CODE)
            finish()
        }
    }

}