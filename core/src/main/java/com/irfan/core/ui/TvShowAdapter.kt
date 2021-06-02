package com.irfan.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.irfan.core.domain.model.TvShow
import com.irfan.core.R
import com.irfan.core.databinding.ItemsMoviesBinding

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ListTvShowViewHolder>() {

    private var listTvShow = ArrayList<TvShow>()
    var onItemClick: ((TvShow) -> Unit)? = null

    fun setTvShow(tvShows: List<TvShow>?){
        if (tvShows == null) return
        listTvShow.clear()
        listTvShow.addAll(tvShows)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTvShowViewHolder {
        val itemsMoviesBinding = ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListTvShowViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: ListTvShowViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class ListTvShowViewHolder(private val mBinding : ItemsMoviesBinding): RecyclerView.ViewHolder(mBinding.root) {
        fun bind(tvShow : TvShow){
            with(mBinding){
                tvTitle.text = tvShow.name
                tvAge.text = tvShow.voteAverage.toString()

            }

            Glide.with(itemView.context)
                .load(itemView.context.getString(R.string.baseUrlImage, tvShow.posterPath))
                .transform(RoundedCorners(20))
                .apply(RequestOptions().override(180,250))
                .into(mBinding.ivPoster)
        }

        init {
            mBinding.root.setOnClickListener {
                onItemClick?.invoke(listTvShow[adapterPosition])
            }
            mBinding.btnDetail.setOnClickListener {
                onItemClick?.invoke(listTvShow[adapterPosition])
            }
        }
    }
}