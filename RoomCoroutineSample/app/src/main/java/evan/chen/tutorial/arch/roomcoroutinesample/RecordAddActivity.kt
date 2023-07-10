package evan.chen.tutorial.arch.roomcoroutinesample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import evan.chen.tutorial.arch.roomcoroutinesample.data.AccountRecord
import evan.chen.tutorial.arch.roomcoroutinesample.databinding.ActivityRecordAddBinding
import evan.chen.tutorial.arch.roomcoroutinesample.utils.DateUtils

class RecordAddActivity : AppCompatActivity() {

    private lateinit var addViewModel: RecordAddViewModel

    private lateinit var binding: ActivityRecordAddBinding

    lateinit var type: String

    companion object {
        const val TYPE = "type"

        fun getIntent(context: Context, type: String): Intent {
            val intent = Intent(context, RecordAddActivity::class.java)
            intent.putExtra(TYPE, type)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecordAddBinding.inflate(layoutInflater)

        setContentView(binding.root)

        type = intent.getStringExtra(RecordAddActivity.TYPE) ?: ""

        addViewModel = ViewModelProvider(this, RecordAddViewModelFactory(application, type)).get(
            RecordAddViewModel::class.java
        )

        addViewModel.categories.observe(this, Observer { list ->

            val adapter = ArrayAdapter(
                this,
                R.layout.dropdown_menu_popup_item,
                list.map { it.name }
            )

            binding.category.setAdapter(adapter)

        })

        binding.category.setOnItemClickListener { adapterView, view, position, id ->
            val value = addViewModel.categories.value
            addViewModel.category = value!![position]
            println(addViewModel.category)
        }

        binding.send.setOnClickListener {

            if (binding.category.text.toString( )== "") {
                setupSnackBar("請選擇類型")
                it.hideKeyboard()
                return@setOnClickListener
            }

            if (binding.money.text.toString( )== "") {
                setupSnackBar("請輸入金額")
                it.hideKeyboard()
                return@setOnClickListener
            }

            val categoryId = addViewModel.category!!.id
            val money = binding.money.text.toString().toInt()
            val memo = binding.memo.text.toString()
            val date = DateUtils().stringToDate(binding.date.text.toString())

            if (date == null) {
                setupSnackBar("請輸入日期，格式:yyyy-MM-dd")
                it.hideKeyboard()
                return@setOnClickListener
            }

            val accountRecord = AccountRecord(0, type, money, memo, categoryId, date)

            addViewModel.insert(accountRecord)

            finish()
        }

        binding.edit.setOnClickListener {
            val intent = CategoryAddActivity.getIntent(this, type)
            startActivityForResult(intent, CategoryAddActivity.REQUEST_CODE)
        }
    }

    private fun setupSnackBar(text:String) {
        Snackbar.make(binding.layout, text, Snackbar.LENGTH_LONG).show()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == CategoryAddActivity.REQUEST_CODE &&
                    resultCode == CategoryAddActivity.RESULT_CODE -> {

            }
        }
    }
}