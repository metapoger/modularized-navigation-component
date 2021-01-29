package com.metapoger.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.metapoger.core.viewBinding
import com.metapoger.navigation.BaseFragment
import com.metapoger.navigationtipsandtricks.NavMainDirections
import com.metapoger.onboarding.databinding.FragmentOnboarding2Binding

class OnBoarding2Fragment: BaseFragment(R.layout.fragment_onboarding_2) {
    private val binding by viewBinding(FragmentOnboarding2Binding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.onBoardingText.text = String.format(getString(R.string.onBoardingText), 2)
        binding.onBoardingText.setOnClickListener {
            findNavController().navigate(OnBoarding2FragmentDirections.actionOnBoarding2FragmentToOnBoarding3Fragment())
        }
        binding.skipOnBoardingBtn.setOnClickListener {
            findNavController().navigate(NavMainDirections.actionGlobalToHome())
        }
    }
}