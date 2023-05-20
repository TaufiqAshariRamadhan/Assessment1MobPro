package org.d3if3110.weebs.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3110.weebs.db.UserDao

class HistoriViewModel(private val db: UserDao) : ViewModel() {
    val data = db.getUser()
    fun hapusData() = viewModelScope.launch { withContext(Dispatchers.IO) {
        db.clearData() }
    }
}
