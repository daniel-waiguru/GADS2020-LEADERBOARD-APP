package tech.danielwaiguru.gads2020.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import tech.danielwaiguru.gads2020.ui.views.iq.IQFragment
import tech.danielwaiguru.gads2020.ui.views.learning.LearningFragment

class MainPagerAdapter(fragmentManager: FragmentManager):
FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    private val fragments = listOf(LearningFragment(), IQFragment())
    private val titles = listOf("Learning Leaders", "Skill IQ Leaders")
    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]
    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}