package br.com.tavieto.groupsgenerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val groups: List<Group> = emptyList()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(group: Group) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(groups[position])
    }

    override fun getItemCount(): Int {
        return groups.size
    }
}