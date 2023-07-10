package evan.chen.tutorial.datastoresample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(val repository: Repository) : ViewModel() {
    private val _userPreferenceLiveData = MutableLiveData<UserPreferences>()
    val userPreferenceLiveData: LiveData<UserPreferences> = _userPreferenceLiveData

    fun updateLanguage(language: String) {
        viewModelScope.launch {
            repository.updateLanguage(language)
        }
    }

    fun updateTheme(theme: String) {
        viewModelScope.launch {
            repository.updateTheme(theme)
        }
    }

    fun getUserPreferences() {
        viewModelScope.launch {
            repository.userPreferencesFlow.collect { data ->
                _userPreferenceLiveData.value = data
            }
        }
    }
}

class TasksViewModelFactory(
    private val repository: Repository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
