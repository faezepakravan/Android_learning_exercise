package com.example.exercideonereggistration.uIFile

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.exercideonereggistration.dataStorage.StoreData
import com.example.exercideonereggistration.dataStorage.dataStore

class ViewModel(private val context: Context) : ViewModel() {
    val Userdata = StoreData(context.dataStore)
    //private val viewModelScope =

    suspend fun savedata(number: String, password: String) {
        //   viewModelScope.launch{ Userdata.saveData(key = number, value = password)}


    }
}