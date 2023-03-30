package org.d3if3110.weebs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if3110.weebs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = MainAdapter(getData())
            setHasFixedSize(true)
        }
    }
    private fun getData(): List<Komik> { return listOf(
        Komik("Regarding Reincarnated to Slime","転生したらスライムだった件",
            "Fuze", "Action, Adventure, Comedy, Fantasy", 12),
        Komik("Kaguya Wants to be Confessed To","かぐや様は告らせたい",
            "Akasaka Aka", "Comedy, Drama, Romance", 12),
        Komik("Bleach","ブリーチ", "Kubo Tite",
            "Action, Adventure, Shonen, Horror, Supernatural", 12),
        Komik("Tomie","富江", "Junji Ito",
            "Pyschological Horror, Supernatural", 12),
    )
    }
}

