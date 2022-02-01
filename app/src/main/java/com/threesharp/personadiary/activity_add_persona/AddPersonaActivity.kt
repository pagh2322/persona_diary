package com.threesharp.personadiary.activity_add_persona

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.threesharp.personadiary.R
import com.threesharp.personadiary.activity_type_detail.PersonaViewModel
import com.threesharp.personadiary.constants.binaryToPT
import com.threesharp.personadiary.database.Persona
import com.threesharp.personadiary.databinding.ActivityAddPersonaBinding

class AddPersonaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddPersonaBinding
    private lateinit var cancelDialog: Dialog
    private lateinit var addDialog: Dialog
    private val personaViewModel: PersonaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPersonaBinding.inflate(layoutInflater)
        init()
        setContentView(binding.root)
    }
    private fun init() {
        initToolbar()
        initDialog()
    }
    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_outline_close_24)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.add_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.tb_add -> addDialog.show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initDialog() {
        cancelDialog = Dialog(this)
        cancelDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        cancelDialog.setContentView(R.layout.custom_dialog)
        cancelDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val tvNo = cancelDialog.findViewById<TextView>(R.id.tv_no)
        tvNo.setOnClickListener { cancelDialog.dismiss() }
        val tvYes = cancelDialog.findViewById<TextView>(R.id.tv_yes)
        tvYes.setOnClickListener { finish() }
        addDialog = Dialog(this)
        addDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        addDialog.setContentView(R.layout.add_dialog)
        addDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val tvCancel = addDialog.findViewById<TextView>(R.id.tv_cancel)
        tvCancel.setOnClickListener { addDialog.dismiss() }
        val tvAdd = addDialog.findViewById<TextView>(R.id.tv_add)
        tvAdd.setOnClickListener {
            var persona = Persona()
            with(binding) {
                persona.name = etName.text.toString()
                if (rbMale.isChecked)
                    persona.sex = 0
                else
                    persona.sex = 1
                val i: Int = if (rbI.isChecked) 0 else 1
                val s: Int = if (rbS.isChecked) 0 else 1
                val t: Int = if (rbT.isChecked) 0 else 1
                val j: Int = if (rbJ.isChecked) 0 else 1
                val type = 1000 * i + 100 * s + 10 * t + j
                persona.type = binaryToPT(type).ordinal
                personaViewModel.insert(persona)
                println(persona)
                finish()
            }
        }
    }
    override fun onBackPressed() {
        cancelDialog.show()
    }
    override fun onStop() {
        super.onStop()
        addDialog.dismiss()
        cancelDialog.dismiss()
    }
}