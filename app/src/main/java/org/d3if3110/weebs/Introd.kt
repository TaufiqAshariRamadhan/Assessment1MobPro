package org.d3if3110.weebs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import org.d3if3110.weebs.databinding.ActivityIntrodBinding
import org.d3if3110.weebs.databinding.ActivityMainBinding

class Introd : AppCompatActivity() {
    companion object {
        var NAMA = "nama"
    }
    private lateinit var binding: ActivityIntrodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntrodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            var nama = binding.namaInput.text.toString()
            binding.textView.text = "Namae wa, " + nama + " desu"
        }
        binding.next.setOnClickListener {
            val klak = Intent(this@Introd, MainActivity::class.java)
            startActivity(klak)
        }
    }
}