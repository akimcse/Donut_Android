package org.gdsc.donut.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.gdsc.donut.R
import org.gdsc.donut.databinding.ActivityGiverMainBinding
import org.gdsc.donut.ui.donation.DonationFragment
import org.gdsc.donut.ui.history.GiverHistoryFragment
import org.gdsc.donut.ui.history.GiverHistoryGiftDetailFragment
import org.gdsc.donut.ui.home.GiverHomeFragment
import org.gdsc.donut.ui.wallet.GiverWalletFragment
import org.gdsc.donut.ui.mypage.MyPageFragment
import org.gdsc.donut.ui.ranking.RankingFragment

class GiverMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGiverMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGiverMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigation()
        setFloatingButton()
    }

    private fun setBottomNavigation() {
        binding.bnvMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_wallet -> changeFragment(getString(R.string.menu_wallet))
                R.id.menu_ranking -> changeFragment(getString(R.string.menu_ranking))
                R.id.menu_my_page -> changeFragment(getString(R.string.menu_my_page))
            }
            true
        }
        changeFragment(getString(R.string.menu_wallet))
    }

    fun changeFragment(tag: String){
        val fragment = supportFragmentManager.findFragmentByTag(tag) ?: when (tag) {
            getString(R.string.menu_wallet) -> GiverWalletFragment()
            getString(R.string.menu_history) -> GiverHistoryFragment()
            getString(R.string.menu_ranking) -> RankingFragment()
            getString(R.string.menu_my_page) -> MyPageFragment()
            "donation" -> DonationFragment()
            "history_detail" -> GiverHistoryGiftDetailFragment()
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

    private fun setFloatingButton(){
        binding.fabDonationBtn.setOnClickListener {
            changeFragment("donation")
        }
    }

    fun enableFloatingButton(){
        binding.fabDonationBtn.visibility = View.GONE
    }
}