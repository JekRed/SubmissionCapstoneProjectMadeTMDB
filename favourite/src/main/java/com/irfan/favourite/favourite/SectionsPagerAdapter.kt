package com.irfan.favourite.favourite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.irfan.capcoba.R

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.favorite_movies, R.string.favorite_tv_shows)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MoviesFavoriteFragment()
            1 -> TvShowFavoriteFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

}