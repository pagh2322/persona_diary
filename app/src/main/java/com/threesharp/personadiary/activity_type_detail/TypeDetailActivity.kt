package com.threesharp.personadiary.activity_type_detail

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.threesharp.personadiary.R
import com.threesharp.personadiary.constants.Personality
import com.threesharp.personadiary.constants.PersonalityList
import com.threesharp.personadiary.constants.PersonalityType
import com.threesharp.personadiary.databinding.ActivityTypeDetailBinding
import com.threesharp.personadiary.utils.Utils
import kotlin.math.abs

class TypeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTypeDetailBinding
    private lateinit var personality: Personality
    private var typeNum: Int = PersonalityType.NONE.ordinal
    private var nsvDrawable: GradientDrawable? = null
    private val personaViewModel: PersonaViewModel by viewModels()
    private lateinit var maleAdapter: PersonaAdapter
    private lateinit var femaleAdapter: PersonaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init() {
        typeNum = intent.getIntExtra("typeNum", PersonalityType.NONE.ordinal)
        personality = PersonalityList.get(typeNum)
        initRV()
        with(binding) {
            personaViewModel.getAll(typeNum).observe(this@TypeDetailActivity, Observer { it ->
                tvTotal.text = it.size.toString()
            })
            personaViewModel.getMale(typeNum).observe(this@TypeDetailActivity, Observer { maleList ->
                maleAdapter.setPersona(maleList)
                tvTotalMale.text = maleList.size.toString()
            })
            personaViewModel.getFemale(typeNum).observe(this@TypeDetailActivity, Observer { femaleList ->
                femaleAdapter.setPersona(femaleList)
                tvTotalFemale.text = femaleList.size.toString()
            })
        }
        nsvDrawable =
            ContextCompat.getDrawable(this, R.drawable.type_detail_nsv) as GradientDrawable?
        initToolbar()
    }
    private fun initRV() {
        maleAdapter = PersonaAdapter { persona -> onBackPressed()}
        femaleAdapter = PersonaAdapter { persona -> onBackPressed()}
        with(binding) {
            rvMale.adapter = maleAdapter
            rvMale.layoutManager = GridLayoutManager(this@TypeDetailActivity, 4)
            rvMale.setHasFixedSize(true)
            rvFemale.adapter = femaleAdapter
            rvFemale.layoutManager = GridLayoutManager(this@TypeDetailActivity, 4)
            rvFemale.setHasFixedSize(true)
        }
    }
    private fun initToolbar() {
        with(binding) {
            llToolbar.setBackgroundColor(getColor(personality.getSColor()))
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_outline_arrow_back_ios_new_24)
            tvPersonality.text = personality.getType()
            root.setBackgroundColor(getColor(personality.getSColor()))
            cvGoodType.setCardBackgroundColor(getColor(personality.getColor()))
            // 스크롤 관련 앱바 설정
            val tv = TypedValue()
            var actionBarHeight = 56
            if (theme.resolveAttribute(androidx.appcompat.R.attr.actionBarSize, tv, true)) {
                actionBarHeight =
                    TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
            }
            val finalActionBarHeight = actionBarHeight
            appbar.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (appBarLayout.totalScrollRange - abs(verticalOffset) <= finalActionBarHeight) {
                    nsvDrawable?.cornerRadius = 0f
                    supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_outline_arrow_back_ios_new_b24)
                } else {
                    nsvDrawable?.cornerRadius = Utils.dpToPx(35).toFloat()
                    supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_outline_arrow_back_ios_new_24)
                }
                nsv.background = nsvDrawable
            })
        }
    }
}