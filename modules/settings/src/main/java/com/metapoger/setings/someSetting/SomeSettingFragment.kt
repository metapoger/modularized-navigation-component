package com.metapoger.setings.someSetting


import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.metapoger.core.viewBinding
import com.metapoger.settings.R
import com.metapoger.settings.databinding.FragmentSomeSettingsBinding

class SomeSettingFragment : Fragment(R.layout.fragment_some_settings) {

    private val binding by viewBinding(FragmentSomeSettingsBinding::bind)

    companion object {
        val RESULT_CODE = SomeSettingFragment::class.java.simpleName + "RESULT_CODE"
        val RESULT_KEY = "RANDOM_KEY"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.settingBtn.setOnClickListener {
            setFragmentResult(RESULT_CODE, bundleOf(RESULT_KEY to "Result from $this"))
            findNavController().navigateUp()
        }
    }
}
