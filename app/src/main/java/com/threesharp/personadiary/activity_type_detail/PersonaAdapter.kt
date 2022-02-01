package com.threesharp.personadiary.activity_type_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.threesharp.personadiary.R
import com.threesharp.personadiary.database.Persona

class PersonaAdapter(val personaItemClick: (Persona) -> Unit) : RecyclerView.Adapter<PersonaAdapter.ViewHolder>() {
    private var personaList: List<Persona> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_persona, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return personaList.size
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(personaList[position])
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tv_personaName)
        fun bind(persona: Persona) {
            tvName.text = persona.name
            itemView.setOnClickListener {
                personaItemClick(persona)
            }
        }
    }

    fun setPersona(personaList: List<Persona>) {
        this.personaList = personaList
        notifyDataSetChanged()
    }
}