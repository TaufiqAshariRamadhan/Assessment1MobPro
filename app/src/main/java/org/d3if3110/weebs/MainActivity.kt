package org.d3if3110.weebs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if3110.weebs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        const val EXTRA_MESSAGE = "com.example.myapp.MESSAGE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val messageTextView = findViewById<TextView>(R.id.messageTextView)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        //messageTextView.text = message
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = MainAdapter(getData())
            setHasFixedSize(true)
        }
    }
    private fun getData(): List<Komik> { return listOf(
        Komik("Reincarnated to Slime","転生したらスライムだった件",
            "Fuze", "Action, Adventure, Comedy, Fantasy", 2012, R.drawable.satu),
        Komik("Love is War","かぐや様は告らせたい",
            "Akasaka Aka", "Comedy, Drama, Romance", 2013, R.drawable.dua),
        Komik("Bleach","ブリーチ", "Kubo Tite",
            "Action, Adventure, Shonen", 2006, R.drawable.empat),
        Komik("Tomie","富江", "Junji Ito",
            "Pyschological Horror, Supernatural", 2014, R.drawable.tiga),
    )
    }

}

