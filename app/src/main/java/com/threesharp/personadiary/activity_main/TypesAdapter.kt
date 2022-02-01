package com.threesharp.personadiary.activity_main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.threesharp.personadiary.R
import com.threesharp.personadiary.constants.PersonalityList

class TypesAdapter(context: Context) : RecyclerView.Adapter<TypesAdapter.ViewHolder>() {
    private var typeNumberList: List<Int> = listOf()
    private val context: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_type, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return typeNumberList.size
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(typeNumberList[position], position)
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvType = itemView.findViewById<TextView>(R.id.tv_type)
        private val tvNum = itemView.findViewById<TextView>(R.id.tv_num)
        private val background = itemView.findViewById<CardView>(R.id.cv_typeView)
        fun bind(typeNumber: Int, position: Int) {
            tvType.text = PersonalityList.get(position).getType()
            tvNum.text = typeNumber.toString()
            background.setCardBackgroundColor(context.getColor(PersonalityList.get(position).getSColor()))
            itemView.setOnClickListener {

            }
        }
    }

    fun setTypes(typeNumberList: List<Int>) {
        this.typeNumberList = typeNumberList
        notifyDataSetChanged()
    }
}