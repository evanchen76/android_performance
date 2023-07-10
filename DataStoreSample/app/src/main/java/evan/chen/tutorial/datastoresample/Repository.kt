package evan.chen.tutorial.datastoresample

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class UserPreferences(
    val language: String,
    val theme: String
)

class Repository(private val dataStore: DataStore<Preferences>) {

    private object PreferencesKeys {
        val LANGUAGE = stringPreferencesKey("language")
        val THEME = stringPreferencesKey("theme")
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .map { preferences ->
            mapUserPreferences(preferences)
        }

    suspend fun updateLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LANGUAGE] = language
        }
    }

    suspend fun updateTheme(theme: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.THEME] = theme
        }
    }

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {
        val language = preferences[PreferencesKeys.LANGUAGE] ?: ""
        val theme = preferences[PreferencesKeys.THEME] ?: ""
        return UserPreferences(language, theme)
    }
}