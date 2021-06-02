package com.irfan.capcoba.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.irfan.core.data.Resource
import com.irfan.core.ui.MoviesAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import com.irfan.capcoba.databinding.FragmentHomeBinding
import com.irfan.capcoba.ui.detail.DetailMovieActivity

class  MoviesFragment : Fragment() {

    private lateinit var _binding : FragmentHomeBinding
    private val binding get() = _binding

    private val moviesViewModel: MoviesViewModel by viewModel()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity != null){
            val moviesAdapter = MoviesAdapter()
            moviesAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_COURSE, selectedData)
                startActivity(intent)
            }


            moviesViewModel.getListMovie.observe(viewLifecycleOwner, { movies->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> _binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            _binding.progressBar.visibility = View.GONE
                            moviesAdapter.setMovies(movies.data)
                        }
                        is Resource.Error -> {
                            _binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(_binding.rvMovies){
                layoutManager = GridLayoutManager(requireContext(),2)
                setHasFixedSize(true)
                this.adapter= moviesAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }

}