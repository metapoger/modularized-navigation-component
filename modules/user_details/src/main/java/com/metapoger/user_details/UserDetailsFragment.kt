package com.metapoger.user_details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.metapoger.core.model.User
import com.metapoger.core.viewBinding
import com.metapoger.navigation.BaseFragment
import com.metapoger.user_details.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : BaseFragment(R.layout.fragment_user_details) {

    private val binding by viewBinding(FragmentUserDetailsBinding::bind)
    private val args by navArgs<UserDetailsFragmentArgs>()
    private val user by lazy {
        args.user
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adjustWithUser(user)
    }

    private fun adjustWithUser(user: User) {
        Glide.with(this)
            .load(user.avatar)
            .into(binding.imageUserAvatar)

        binding.textUserName.text = user.name
    }
}


