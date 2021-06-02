package com.irfan.favourite.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.irfan.favourite.databinding.FragmentFavoritBinding
import com.irfan.favourite.di.favoriteModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

@Suppress("UNREACHABLE_CODE")
class FavoriteFragment : Fragment() {

    private lateinit var fragmentFavoriteBinding: FragmentFavoritBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(favoriteModule)
        fragmentFavoriteBinding = FragmentFavoritBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteBinding.root
        unloadKoinModules(favoriteModule)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionsPagerAdapter = SectionsPagerAdapter(requireActivity(), childFragmentManager)
        fragmentFavoriteBinding.viewPager.adapter = sectionsPagerAdapter
        fragmentFavoriteBinding.tabs.setupWithViewPager(fragmentFavoriteBinding.viewPager)

    }
}