package org.d3if3110.weebs.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if3110.weebs.R
import org.d3if3110.weebs.model.Komik

class MainViewModel : ViewModel() {

    private val data = MutableLiveData<List<Komik>>()
    init {
        data.value = initData()
    }

    private fun initData(): List<Komik> { return listOf(
        Komik("Reincarnated to Slime","転生したらスライムだった件",
            "Fuze", "Action, Adventure, Fantasy", 2012, R.drawable.satu),
        Komik("Love is War","かぐや様は告らせたい",
            "Akasaka Aka", "Comedy, Drama, Romance", 2013, R.drawable.dua),
        Komik("Bleach","ブリーチ", "Kubo Tite",
            "Action, Adventure, Shonen", 2006, R.drawable.empat),
        Komik("Tomie","富江", "Junji Ito",
            "Pyschological Horror, Supernatural", 2014, R.drawable.tiga),
    )
    }
    fun getData(): LiveData<List<Komik>> = data
}