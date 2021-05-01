package br.com.tavieto.groupsgenerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var groupsList: List<Group> = emptyList()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(group: Group) {
            itemView.findViewById<AppCompatTextView>(R.id.number_group).text = group.id.toString()
            itemView.findViewById<AppCompatTextView>(R.id.number_total).text = group.total.toString()
            itemView.findViewById<AppCompatTextView>(R.id.numbers).text = group.numbers.toString().subSequence(1..(group.numbers.toString().length - 2))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(groupsList[position])
    }

    override fun getItemCount(): Int {
        return groupsList.size
    }

    fun setItems(listItems: List<Group>) {
        groupsList = listItems
        notifyDataSetChanged()
    }
}