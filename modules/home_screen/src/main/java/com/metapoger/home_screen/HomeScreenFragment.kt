package com.metapoger.home_screen

import android.os.Bundle
import android.view.View
import com.metapoger.home_screen.databinding.FragmentHomeScreenBinding
import com.metapoger.core.viewBinding
import com.metapoger.navigation.TabNavigationFragment

class HomeScreenFragment : TabNavigationFragment(R.layout.fragment_home_screen) {

    private val binding by viewBinding(FragmentHomeScreenBinding::bind)

    override val containerId: Int = R.id.homeScreenContainer
    override val tabs by lazy {
        listOf(
            Tab(getString(R.string.usersTabName), R.navigation.nav_user_flow),
            Tab(getString(R.string.settingsTabName), R.navigation.nav_settings)
        )
    }
    private lateinit var selectedTab: Tab

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedTab = tabs[0]
        selectTab(tabs[0])

        binding.navBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.users -> {
                    if (selectedTab.name != tabs[0].name) selectTab(tabs[0])
                    true
                }
                R.id.settings -> {
                    if (selectedTab.name != tabs[1].name) selectTab(tabs[1])
                    true
                }
                else -> {
                    false
                }
            }
        }

    }

    override fun tabSelected(tab: Tab) {
        selectedTab = tab
    }
}
