package com.metapoger.splash

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.metapoger.navigation.BaseFragment
import com.metapoger.navigationtipsandtricks.NavMainDirections

private const val FIRST_TIME_KEY = "FIRST_TIME_KEY"

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    private val prefs by lazy {
        requireContext().getSharedPreferences(this.javaClass.simpleName, Context.MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            if (prefs.getBoolean(FIRST_TIME_KEY, false)) {
                findNavController().navigate(NavMainDirections.actionGlobalToHome())
            } else {
                with(prefs.edit()) {
                    putBoolean(FIRST_TIME_KEY, true)
                    commit()
                }
                findNavController().navigate(NavMainDirections.actionGlobalToOnboarding())
            }
        }, 1000)
    }

}