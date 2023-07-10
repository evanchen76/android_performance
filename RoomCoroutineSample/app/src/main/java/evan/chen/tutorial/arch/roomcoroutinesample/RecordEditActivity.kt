package evan.chen.tutorial.arch.roomcoroutinesample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import evan.chen.tutorial.arch.roomcoroutinesample.data.AccountRecord
import evan.chen.tutorial.arch.roomcoroutinesample.databinding.ActivityRecordEditBinding
import evan.chen.tutorial.arch.roomcoroutinesample.utils.DateUtils

class RecordEditActivity : AppCompatActivity() {

    private lateinit var editViewModel: RecordEditViewModel

    private lateinit var binding: ActivityRecordEditBinding

    private var accountRecordId:Int = 0

    companion object {
        const val Account_Record_ID = "accountRecordId"

        fun getIntent(context: Context, accountRecordId: Int): Intent {
            val intent = Intent(context, RecordEditActivity::class.java)
            intent.putExtra(Account_Record_ID, accountRecordId)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecordEditBinding.inflate(layoutInflater)

        setContentView(binding.root)

        accountRecordId = intent.getIntExtra(Account_Record_ID, 0)

        editViewModel = ViewModelProvider(this, RecordEditViewModelFactory(application, accountRecordId)).get(RecordEditViewModel::class.java)

        editViewModel.accountRecord.observe(this, Observer {

            binding.type.text = it.type
            binding.categoryName.text = it.categoryName.toString()
            binding.money.setText(it.money.toString())
            binding.memo.setText(it.memo.toString())
            binding.date.setText(it.date.toString())
        })

        binding.delete.setOnClickListener {
            editViewModel.delete(accountRecordId)
            finish()
        }

        binding.update.setOnClickListener {

            if (binding.money.text.toString( )== "") {
                setupSnackBar("請輸入金額")
                return@setOnClickListener
            }

            val money = binding.money.text.toString().toInt()
            val memo = binding.memo.text.toString()
            val date = DateUtils().stringToDate(binding.date.text.toString())

            val categoryId = editViewModel.accountRecord.value!!.categoryId
            val type = editViewModel.accountRecord.value!!.type

            if (date == null) {
                setupSnackBar("請輸入日期，格式:yyyy-MM-dd")
                it.hideKeyboard()
                return@setOnClickListener
            }

            val accountRecord = AccountRecord(0, type, money, memo, categoryId, date)

            editViewModel.update(accountRecord)
            finish()
        }

    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun setupSnackBar(text:String) {
        Snackbar.make(binding.layout, text, Snackbar.LENGTH_LONG).show()
    }

}