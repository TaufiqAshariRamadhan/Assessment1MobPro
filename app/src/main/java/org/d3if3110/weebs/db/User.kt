package org.d3if3110.weebs.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var tanggal: Long = System.currentTimeMillis(),
    val nama: String
    )
