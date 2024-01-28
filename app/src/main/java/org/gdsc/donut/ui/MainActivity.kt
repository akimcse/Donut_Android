package org.gdsc.donut.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.gdsc.donut.R
import org.gdsc.donut.databinding.ActivityMainBinding
import org.gdsc.donut.ui.history.HistoryFragment
import org.gdsc.donut.ui.home.HomeFragment
import org.gdsc.donut.ui.mypage.MyPageFragment
import org.gdsc.donut.ui.ranking.RankingFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        binding.bnvMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> changeFragment(getString(R.string.menu_home))
                R.id.menu_history -> changeFragment(getString(R.string.menu_history))
                R.id.menu_ranking -> changeFragment(getString(R.string.menu_ranking))
                R.id.menu_my_page -> changeFragment(getString(R.string.menu_my_page))
            }
            true
        }
        changeFragment(getString(R.string.menu_home))
    }

    private fun changeFragment(tag: String){
        val fragment = supportFragmentManager.findFragmentByTag(tag) ?: when (tag) {
            getString(R.string.menu_home) -> HomeFragment()
            getString(R.string.menu_history) -> HistoryFragment()
            getString(R.string.menu_ranking) -> RankingFragment()
            getString(R.string.menu_my_page) -> MyPageFragment()
            else -> null
        } ?: return

        if (fragment.isVisible) return
        val transaction = supportFragmentManager.beginTransaction()

        supportFragmentManager.fragments
            .filter { it.isVisible }
            .forEach {
                transaction.hide(it)
            }

        if (fragment.isAdded) {
            transaction.show(fragment)
        } else {
            transaction.add(R.id.fcv_main, fragment, tag)
        }

        transaction.commit()
    }
}