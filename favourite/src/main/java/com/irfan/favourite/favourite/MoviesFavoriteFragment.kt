package com.irfan.favourite.favourite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.core.ui.MoviesAdapter
import com.irfan.capcoba.ui.detail.DetailMovieActivity
import com.irfan.favourite.databinding.FragmentMoviesFavoritBinding
import org.koin.android.viewmodel.ext.android.viewModel


class MoviesFavoriteFragment : Fragment() {

    private lateinit var _binding: FragmentMoviesFavoritBinding
    private val binding get() = _binding


    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesFavoritBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val moviesAdapter = MoviesAdapter()
            moviesAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_COURSE, selectedData)
                startActivity(intent)
            }


            favoriteViewModel.getListFavoriteMovies.observe(viewLifecycleOwner, { dataMovies ->
               moviesAdapter.setMovies(dataMovies)
               binding.viewEmpty.root.visibility = if (dataMovies.isNullOrEmpty()) View.VISIBLE else View.GONE
           })


            with(binding.rvFavMovie) {
                this.layoutManager = LinearLayoutManager(context)
                this.setHasFixedSize(true)
                this.adapter = moviesAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }

}