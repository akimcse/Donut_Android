package org.gdsc.donut.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.gdsc.donut.R
import org.gdsc.donut.databinding.ActivityReceiverMainBinding
import org.gdsc.donut.ui.history.GiverHistoryFragment
import org.gdsc.donut.ui.history.ReceiverHistoryFragment
import org.gdsc.donut.ui.home.GiverHomeFragment
import org.gdsc.donut.ui.home.ReceiverHomeFragment
import org.gdsc.donut.ui.mypage.MyPageFragment
import org.gdsc.donut.ui.ranking.RankingFragment

class ReceiverMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReceiverMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiverMainBinding.inflate(layoutInflater)
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
            getString(R.string.menu_home) -> ReceiverHomeFragment()
            getString(R.string.menu_history) -> ReceiverHistoryFragment()
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