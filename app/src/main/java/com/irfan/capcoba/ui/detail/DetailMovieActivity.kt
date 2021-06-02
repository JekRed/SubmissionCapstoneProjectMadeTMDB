package com.irfan.capcoba.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.irfan.capcoba.R
import com.irfan.core.data.Resource
import com.irfan.core.domain.model.Movies
import com.irfan.core.domain.model.TvShow
import com.irfan.core.ui.MoviesHorizontalAdapter
import com.irfan.capcoba.databinding.ActivityDetailBinding
import com.irfan.capcoba.databinding.DetailMoviesBinding
import com.irfan.capcoba.ui.HomeActivity
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var detailMoviesBinding: DetailMoviesBinding
    private val detailMovieViewModel: DetailMovieViewModel by viewModel()

    companion object {
        const val EXTRA_COURSE = "extra_course"
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailMoviesBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailMoviesBinding = activityDetailMoviesBinding.detailMovies

        setContentView(activityDetailMoviesBinding.root)



        val backArrow : ImageView = findViewById(R.id.imageView2)
        backArrow.setOnClickListener(this)

        val detailMovies = intent.getParcelableExtra<Movies>(EXTRA_COURSE)
        if (detailMovies != null) {
            showDetailMovies(detailMovies)
        }

        val detailTvShow = intent.getParcelableExtra<TvShow>(EXTRA_TVSHOW)
        if (detailTvShow != null) {
            showDetailTvShow(detailTvShow)
        }

        val horizontalAdapter = MoviesHorizontalAdapter()
        horizontalAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(EXTRA_COURSE, selectedData)
            startActivity(intent)
        }
        detailMovieViewModel.getListMovie.observe(this, { movies->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> activityDetailMoviesBinding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        activityDetailMoviesBinding.progressBar.visibility = View.GONE
                        horizontalAdapter.setMoviesHorizontal(movies.data)
                    }
                    is Resource.Error -> {
                        activityDetailMoviesBinding.progressBar.visibility = View.GONE
                        Toast.makeText(this@DetailMovieActivity, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(detailMoviesBinding.rcList){
            isNestedScrollingEnabled = true
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
            this.adapter = horizontalAdapter
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
        }

    }

    private fun showDetailMovies(detailMovies: Movies?) {

        detailMovies?.let {
            detailMoviesBinding.tvmainTitle.text = detailMovies.title
            detailMoviesBinding.tvsubTitle.text = detailMovies.originalTitle
            detailMoviesBinding.tvmainDes.text = detailMovies.overview
            detailMoviesBinding.rtmainBar.rating = detailMovies.voteAverage.div(2)
            detailMoviesBinding.tvmainAge.text = detailMovies.voteAverage.toString()

            Glide.with(this@DetailMovieActivity)
                .load(getString(R.string.baseUrlImage, detailMovies.posterPath))
                .transform(RoundedCorners(20))
                .apply(RequestOptions().override(130,190))
                .into(detailMoviesBinding.ivdetailPoster)

            var statusFavorite = detailMovies.isFavorite
            setStatusFavorite(statusFavorite)
            detailMoviesBinding.fab.setOnClickListener{
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovies, statusFavorite)
                setStatusFavorite(statusFavorite)
            }

        }
    }

    private fun showDetailTvShow(detailTvShow: TvShow?) {


        with(detailMoviesBinding.rcList){
            isNestedScrollingEnabled = true
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
        }

        detailTvShow?.let {
            detailMoviesBinding.tvmainTitle.text = detailTvShow.name
            detailMoviesBinding.tvsubTitle.text = detailTvShow.originalName
            detailMoviesBinding.tvmainDes.text = detailTvShow.overview
            detailMoviesBinding.rtmainBar.rating = detailTvShow.voteAverage.div(2)
            detailMoviesBinding.tvmainAge.text = detailTvShow.voteAverage.toString()

            Glide.with(this@DetailMovieActivity)
                .load(getString(R.string.baseUrlImage, detailTvShow.posterPath))
                .transform(RoundedCorners(20))
                .apply(RequestOptions().override(130,190))
                .into(detailMoviesBinding.ivdetailPoster)

            var statusFavorite = detailTvShow.isFavorite
            setStatusFavorite(statusFavorite)
            detailMoviesBinding.fab.setOnClickListener{
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteTvShow(detailTvShow, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(favorite: Boolean){
        if(favorite){
            detailMoviesBinding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        }else{
            detailMoviesBinding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_empty))
        }

    }



    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageView2 -> {
                val moveIntent = Intent(this@DetailMovieActivity, HomeActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}


