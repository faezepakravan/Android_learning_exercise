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

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("storeData")

class StoreData(private val preferenceDatastore: DataStore<Preferences>) {

    companion object {

        val numberKey = stringPreferencesKey("NUMBER")
        val passwordKey = stringPreferencesKey("PASSWORD")
    }

    suspend fun getData(key: Preferences.Key<String>): String? {
        val preferences = preferenceDatastore.data.first()
        return preferences[key]

    }

    /*  val getData: Flow<String?> = context.dataStore.data
          .map { preferences ->
              preferences[USERNAME] ?: ""

          }*/
    suspend fun saveData(dataStoreKey: Preferences.Key<String>, value: String) {
        preferenceDatastore.edit { preferences ->
            preferences[dataStoreKey] = value
        }
    }
}