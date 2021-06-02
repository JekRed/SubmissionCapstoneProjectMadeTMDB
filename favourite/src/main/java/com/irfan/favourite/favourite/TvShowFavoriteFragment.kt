package com.irfan.favourite.favourite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.core.ui.TvShowAdapter
import com.irfan.capcoba.ui.detail.DetailMovieActivity
import com.irfan.favourite.databinding.FragmentTvShowFavoritBinding
import org.koin.android.viewmodel.ext.android.viewModel


class TvShowFavoriteFragment : Fragment() {

    private lateinit var _binding: FragmentTvShowFavoritBinding
    private val binding get() = _binding


    private val favoriteViewModel: FavoriteViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowFavoritBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_TVSHOW, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.getListFavoriteTvShows.observe(viewLifecycleOwner, { dataMovies ->
                tvShowAdapter.setTvShow(dataMovies)
                binding.viewEmpty.root.visibility = if (dataMovies.isNullOrEmpty()) View.VISIBLE else View.GONE
            })

            with(binding.rvFavTvshwo) {
                this.layoutManager = LinearLayoutManager(context)
                this.setHasFixedSize(true)
                this.adapter = tvShowAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }

}