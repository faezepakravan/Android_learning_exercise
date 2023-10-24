package com.example.exercideonereggistration.UIFile

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.exercideonereggistration.dataStorage.StoreData

class ViewModel(private val context:Context) : ViewModel(){
    val Userdata = StoreData(context)
    //private val viewModelScope =

     suspend fun savedata (number : String , password : String){
     //   viewModelScope.launch{ Userdata.saveData(key = number, value = password)}


    }
}