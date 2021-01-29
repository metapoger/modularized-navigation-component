package com.metapoger.usersList

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.LinearLayoutManager
import com.metapoger.core.model.User
import com.metapoger.core.viewBinding
import com.metapoger.navigation.BaseFragment
import com.metapoger.usersList.databinding.FragmentUserListBinding

class UserListFragment : BaseFragment(R.layout.fragment_user_list) {

    private val usersList by lazy {
        ArrayList<User>().apply {
            add(User(ContextCompat.getDrawable(requireContext(), R.drawable.user1), "Leonard"))
            add(User(ContextCompat.getDrawable(requireContext(), R.drawable.user2), "Dewey"))
            add(User(ContextCompat.getDrawable(requireContext(), R.drawable.user3), "Marie"))
            add(User(ContextCompat.getDrawable(requireContext(), R.drawable.user4), "Hollie"))
            add(User(ContextCompat.getDrawable(requireContext(), R.drawable.user5), "Umar"))
            add(User(ContextCompat.getDrawable(requireContext(), R.drawable.user6), "Kyla"))
            add(User(ContextCompat.getDrawable(requireContext(), R.drawable.user7), "Jack"))
            add(User(ContextCompat.getDrawable(requireContext(), R.drawable.user8), "Alfred"))
            add(User(ContextCompat.getDrawable(requireContext(), R.drawable.user9), "Marvin"))
            add(User(ContextCompat.getDrawable(requireContext(), R.drawable.user10), "Tanya"))
        }
    }
    private val binding by viewBinding(FragmentUserListBinding::bind)
    private var sharedElement: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AdapterUserList(usersList) { v, userId, position ->
                val holder = binding.recyclerView.findViewHolderForAdapterPosition(position)
                if (holder?.itemView != null) {
                    sharedElement = holder.itemView.findViewById(R.id.avatarImageView)
                    findNavController().navigate(
                        UserListFragmentDirections.actionUserFromListToDetails(userId),
                        FragmentNavigator.Extras.Builder()
                            .addSharedElement(sharedElement!!, "userAvatarTN").build()
                    )
                }
            }
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
    }
}