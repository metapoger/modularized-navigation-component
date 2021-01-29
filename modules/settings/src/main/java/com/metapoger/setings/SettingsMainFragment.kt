package com.metapoger.setings


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.metapoger.core.viewBinding
import com.metapoger.setings.someSetting.SomeSettingFragment
import com.metapoger.settings.R
import com.metapoger.settings.databinding.FragmentSettingsMainBinding

class SettingsMainFragment : Fragment(R.layout.fragment_settings_main) {

    private val binding by viewBinding(FragmentSettingsMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener(SomeSettingFragment.RESULT_CODE) { _, bundle ->
            Toast.makeText(
                context,
                bundle.getString(SomeSettingFragment.RESULT_KEY),
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.someSettingBtn.setOnClickListener {
            findNavController().navigate(SettingsMainFragmentDirections.actionSettingsMainFragmentToSomeSetingsFragment())
        }
    }
}
