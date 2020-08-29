package tech.danielwaiguru.gads2020.ui.views.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import tech.danielwaiguru.gads2020.R
import tech.danielwaiguru.gads2020.adapters.MainPagerAdapter

class MainActivity : AppCompatActivity() {
    private val mainPagerAdapter: MainPagerAdapter by lazy {
        MainPagerAdapter(supportFragmentManager)
    }
    companion object {
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
        initUi()
    }
    private fun initUi(){
        tabs.setupWithViewPager(fragmentPager)
        fragmentPager.adapter = mainPagerAdapter
    }
}