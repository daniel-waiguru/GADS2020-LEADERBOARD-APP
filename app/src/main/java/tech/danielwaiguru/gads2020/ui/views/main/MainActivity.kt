package tech.danielwaiguru.gads2020.ui.views.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import tech.danielwaiguru.gads2020.R
import tech.danielwaiguru.gads2020.adapters.MainPagerAdapter
import tech.danielwaiguru.gads2020.ui.views.submit.SubmitActivity

@AndroidEntryPoint
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
        setContentView(R.layout.activity_main)
        initUi()
        toolbar.title = getString(R.string.toolbar_title)
        setSupportActionBar(toolbar)
        initListeners()
    }
    private fun initListeners(){
        initSubmitButton.setOnClickListener { initSubmitActivity() }
    }
    private fun initUi(){
        tabs.setupWithViewPager(fragmentPager)
        fragmentPager.adapter = mainPagerAdapter
    }
    private fun initSubmitActivity(){
        startActivity(Intent(this, SubmitActivity::class.java))
    }
}