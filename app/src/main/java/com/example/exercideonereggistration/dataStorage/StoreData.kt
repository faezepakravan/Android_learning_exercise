package com.example.exercideonereggistration.dataStorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class StoreData(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("storeData")
        public val numberKey = stringPreferencesKey("NUMBER")
        public val passwordKey= stringPreferencesKey("PASSWORD")

    }

    suspend fun getData(key: Preferences.Key<String>): String? {
        val preferences = context.dataStore.data.first()
        return preferences[key]

    }

    /*  val getData: Flow<String?> = context.dataStore.data
          .map { preferences ->
              preferences[USERNAME] ?: ""

          }*/
    suspend fun saveData(dataStoreKey: Preferences.Key<String>, value: String) {
        context.dataStore.edit { preferences ->
            preferences[dataStoreKey] = value
        }
    }


}