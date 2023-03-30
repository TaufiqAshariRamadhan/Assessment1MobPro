package org.d3if3110.weebs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class Introd : AppCompatActivity() {
    companion object {
        var NAMA = "nama"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introd)
        val klik = findViewById<Button>(R.id.button)
        val klak = findViewById<Button>(R.id.next)

        klik.setOnClickListener {
            val messageTextView = findViewById<TextView>(R.id.nama_input)
            var nm_out = findViewById<TextView>(R.id.textView)
            var nama = messageTextView.text.toString()
            nm_out.text = "Namae wa, " + nama + " desu"
        }
        klak.setOnClickListener {
            val klak = Intent(this@Introd, MainActivity::class.java)
            startActivity(klak)
        }
    }
}