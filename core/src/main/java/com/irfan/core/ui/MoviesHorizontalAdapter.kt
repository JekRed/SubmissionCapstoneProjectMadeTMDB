package com.irfan.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.irfan.core.R
import com.irfan.core.domain.model.Movies
import com.irfan.core.databinding.ItemsHorizontalMoviesBinding

class MoviesHorizontalAdapter: RecyclerView.Adapter<MoviesHorizontalAdapter.ListMoviesHorizontalViewHolder>()   {

    private var listMoviesHorizontal = ArrayList<Movies>()
    var onItemClick: ((Movies) -> Unit)? = null

    fun setMoviesHorizontal(movies: List<Movies>?){
        if (movies == null) return
        listMoviesHorizontal.clear()
        listMoviesHorizontal.addAll(movies)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListMoviesHorizontalViewHolder {
        val itemsHorizontalMoviesBinding = ItemsHorizontalMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListMoviesHorizontalViewHolder(itemsHorizontalMoviesBinding)
    }

    override fun onBindViewHolder(holder: ListMoviesHorizontalViewHolder, position: Int) {
        val moviesHorizontal = listMoviesHorizontal[position]
        holder.bind(moviesHorizontal)
    }

    override fun getItemCount(): Int = listMoviesHorizontal.size

    inner class ListMoviesHorizontalViewHolder (private val binding: ItemsHorizontalMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(moviesHorizontal : Movies){
            with(binding){
                tvTitle.text = moviesHorizontal.title
                ratingBar.rating = moviesHorizontal.voteAverage.div(2)
                tvAge.text = moviesHorizontal.voteAverage.toString()

            }

            Glide.with(itemView.context)
                .load(itemView.context.getString(R.string.baseUrlImage, moviesHorizontal.posterPath))
                .transform(RoundedCorners(20))
                .apply(RequestOptions().override(180,250))
                .into(binding.ivPoster)

        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMoviesHorizontal[adapterPosition])
            }
            binding.ivPoster.setOnClickListener {
                onItemClick?.invoke(listMoviesHorizontal[adapterPosition])
            }
        }

    }

}