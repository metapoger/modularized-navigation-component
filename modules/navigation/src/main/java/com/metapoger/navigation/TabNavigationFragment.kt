package com.metapoger.navigation

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

abstract class TabNavigationFragment(layoutId: Int) : BaseFragment(layoutId) {

    inner class Tab(val name: String, val graphId: Int)

    abstract val containerId: Int
    abstract val tabs: List<Tab>
    abstract fun tabSelected(tab: Tab)

    lateinit var currentNavController: NavController

    private var selectedFragment: NavHostFragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentNavController = findNavController()

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val isNavigatedUp = currentNavController.navigateUp()
                if(isNavigatedUp){
                    return
                }else{
                    activity?.finish()
                }
            }
        })
    }

    protected fun selectTab(tab: Tab) {
        val newFragment = obtainNavHostFragment(
                childFragmentManager,
                getFragmentTag(tabs.indexOf(tab)),
                tab.graphId,
                containerId
        )

        val fTrans = childFragmentManager.beginTransaction()
        with(fTrans) {
            if (selectedFragment != null) detach(selectedFragment!!)
            attach(newFragment)
            commitNow()
        }
        selectedFragment = newFragment
        currentNavController = selectedFragment!!.navController
        tabSelected(tab)
    }

    private fun obtainNavHostFragment(
        fragmentManager: FragmentManager,
        fragmentTag: String,
        navGraphId: Int,
        containerId: Int
    ): NavHostFragment {
        // If the Nav Host fragment exists, return it
        val existingFragment = fragmentManager.findFragmentByTag(fragmentTag) as NavHostFragment?
        existingFragment?.let { return it }

        // Otherwise, create it and return it.
        val navHostFragment = NavHostFragment.create(navGraphId)
        fragmentManager.beginTransaction()
            .add(containerId, navHostFragment, fragmentTag)
            .commitNow()
        return navHostFragment
    }

    private fun getFragmentTag(index: Int) = "bottomNavigation#$index"
}