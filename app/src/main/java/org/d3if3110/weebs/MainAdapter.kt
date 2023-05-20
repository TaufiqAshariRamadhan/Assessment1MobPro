package org.d3if3110.weebs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if3110.weebs.databinding.ListItemBinding
import org.d3if3110.weebs.db.User
import org.d3if3110.weebs.model.Komik

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(komik: Komik) = with(binding) {
            judul.text = komik.judul
            romaji.text = komik.romaji
            author.text = komik.author
            genre.text = komik.genre
            tahun.text = komik.tahun.toString()
            imageView.setImageResource(komik.imageResId)
        }
    }

    private val data = mutableListOf<Komik>()
    fun updateData(newData: List<Komik>) { data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}