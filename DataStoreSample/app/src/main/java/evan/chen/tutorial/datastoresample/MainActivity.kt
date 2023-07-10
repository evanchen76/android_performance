package evan.chen.tutorial.datastoresample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import evan.chen.tutorial.datastoresample.databinding.ActivityMainBinding

private const val USER_PREFERENCES_NAME = "user_preferences"
private val Context.dataStore by preferencesDataStore(USER_PREFERENCES_NAME)

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            TasksViewModelFactory(
                Repository(dataStore)
            )
        ).get(MainViewModel::class.java)

        binding.themeDark.setOnClickListener {
            viewModel.updateTheme("Dark")
        }

        binding.themeLight.setOnClickListener {
            viewModel.updateTheme("Light")
        }

        binding.languageENUS.setOnClickListener {
            viewModel.updateLanguage("EN-US")
        }

        binding.languageZHTW.setOnClickListener {
            viewModel.updateLanguage("ZH-TW")
        }

        viewModel.getUserPreferences()

        viewModel.userPreferenceLiveData.observe(this) { userPreference ->
            binding.theme.text = "主題：${userPreference.theme}"
            binding.language.text = "語言：${userPreference.language}"
        }
    }
}