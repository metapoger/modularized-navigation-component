package com.metapoger.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.metapoger.core.viewBinding
import com.metapoger.navigation.BaseFragment
import com.metapoger.navigationtipsandtricks.NavMainDirections
import com.metapoger.onboarding.databinding.FragmentOnboarding1Binding

class OnBoarding1Fragment: BaseFragment(R.layout.fragment_onboarding_1) {
    private val binding by viewBinding(FragmentOnboarding1Binding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.onBoardingText.text = String.format(getString(R.string.onBoardingText), 1)
        binding.onBoardingText.setOnClickListener {
            findNavController().navigate(OnBoarding1FragmentDirections.actionOnBoarding1FragmentToOnBoarding2Fragment())
        }
        binding.skipOnBoardingBtn.setOnClickListener {
            findNavController().navigate(NavMainDirections.actionGlobalToHome())
        }
    }
}