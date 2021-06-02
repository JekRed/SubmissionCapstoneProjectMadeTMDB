package com.irfan.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.irfan.core.R
import com.irfan.core.databinding.ItemsMoviesBinding
import com.irfan.core.domain.model.Movies

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ListMoviesViewHolder>()  {

    private var listMovies = ArrayList<Movies>()
    var onItemClick: ((Movies) -> Unit)? = null

    fun setMovies(movies: List<Movies>?){
        if (movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMoviesViewHolder {
        val itemsMoviesBinding = ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListMoviesViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: ListMoviesViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size



    inner class ListMoviesViewHolder(private val mBinding : ItemsMoviesBinding): RecyclerView.ViewHolder(mBinding.root) {
        fun bind(movie: Movies) {
            with(mBinding) {
                tvTitle.text = movie.title
                tvAge.text = movie.voteAverage.toString()


            }

            Glide.with(itemView.context)
                .load(itemView.context.getString(R.string.baseUrlImage, movie.posterPath))
                .transform(RoundedCorners(20))
                .apply(RequestOptions().override(180, 250))
                .into(mBinding.ivPoster)
        }

        init {
            mBinding.root.setOnClickListener {
                onItemClick?.invoke(listMovies[adapterPosition])
            }
            mBinding.btnDetail.setOnClickListener {
                onItemClick?.invoke(listMovies[adapterPosition])
            }
        }
    }
}