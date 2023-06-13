package org.d3if3110.weebs.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3110.weebs.R
import org.d3if3110.weebs.model.Komik
import org.d3if3110.weebs.network.KomikApi
import org.d3if3110.weebs.network.UpdateWorker
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {

    private val data = MutableLiveData<List<Komik>>()
    private val status = MutableLiveData<KomikApi.ApiStatus>()
    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(KomikApi.ApiStatus.LOADING)
            try {
                data.postValue(KomikApi.service.getKomik())
                status.postValue(KomikApi.ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.postValue(KomikApi.ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Komik>> = data
    fun getStatus(): LiveData<KomikApi.ApiStatus> = status
    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME, ExistingWorkPolicy.REPLACE,
            request
        ) }

}