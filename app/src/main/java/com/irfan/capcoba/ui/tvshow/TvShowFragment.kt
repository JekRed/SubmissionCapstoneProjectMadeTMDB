package com.irfan.capcoba.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.irfan.core.data.Resource
import com.irfan.core.ui.TvShowAdapter
import com.irfan.capcoba.databinding.FragmentTvShowBinding
import com.irfan.capcoba.ui.detail.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {


    private var _binding : FragmentTvShowBinding? = null
    private val binding get() = _binding!!
    private val tvShowViewModel: TvShowViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity != null){
            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_TVSHOW, selectedData)
                startActivity(intent)
            }



            tvShowViewModel.getListTvShow.observe(viewLifecycleOwner, {tvShow ->
                if (tvShow != null) {
                    when (tvShow) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            tvShowAdapter.setTvShow(tvShow.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding.rvMovies){
                layoutManager = GridLayoutManager(requireContext(),2)
                setHasFixedSize(true)
                this.adapter= tvShowAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}