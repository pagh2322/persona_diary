package com.threesharp.personadiary

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.threesharp.personadiary.activity_add_persona.AddPersonaActivity
import com.threesharp.personadiary.activity_main.TypesAdapter
import com.threesharp.personadiary.activity_type_detail.PersonaAdapter
import com.threesharp.personadiary.activity_type_detail.PersonaViewModel
import com.threesharp.personadiary.activity_type_detail.TypeDetailActivity
import com.threesharp.personadiary.constants.PersonalityType
import com.threesharp.personadiary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var typesAdapter: TypesAdapter
    private val personaViewModel: PersonaViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        init()
        setContentView(binding.root)
    }
    private fun init() {
        initRV()
        with(binding) {
            personaViewModel.getAll().observe(this@MainActivity, Observer { it ->
                tvTotalNumber.text = it.size.toString()
                typesAdapter.setTypes(listOf(1,2,3))
            })
        }
        initBottomAppbar()
        initColor()
    }
    private fun initRV() {
        typesAdapter = TypesAdapter(this)
        with(binding) {
            rvTypes.adapter = typesAdapter
            rvTypes.layoutManager = GridLayoutManager(this@MainActivity, 4)
            rvTypes.setHasFixedSize(true)
        }
    }
    private fun initBottomAppbar() {
        with(binding) {
            bab.replaceMenu(R.menu.main_bottom_appbar_menu)
            setSupportActionBar(bab)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_outline_web_asset_24)
            fab.setOnClickListener {
                val addPersonaIntent = Intent(applicationContext, AddPersonaActivity::class.java)
                startActivity(addPersonaIntent)
            }
        }
    }
    private fun initColor() {
        with(binding) {
            fab.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.ISTJ))
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_bottom_appbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val siteIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.16personalities.com/ko/%EB%AC%B4%EB%A3%8C-%EC%84%B1%EA%B2%A9-%EC%9C%A0%ED%98%95-%EA%B2%80%EC%82%AC")
                )
                startActivity(siteIntent)
            }
            R.id.ab_relation -> {
                val testIntent = Intent(this, TypeDetailActivity::class.java)
                testIntent.putExtra("typeNum", PersonalityType.ISTJ.ordinal)
                startActivity(testIntent)
            }
//            R.id.ab_notRelation -> {}
            R.id.ab_me -> {
//                val myInfoIntent = Intent(applicationContext, MeActivity::class.java)
//                startActivity(myInfoIntent)
            }
            R.id.ab_setting -> {}
        }
        return super.onOptionsItemSelected(item)
    }
}