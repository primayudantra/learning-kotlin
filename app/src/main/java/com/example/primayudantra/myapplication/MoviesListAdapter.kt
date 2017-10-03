package com.example.primayudantra.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by primayudantra on 10/3/17.
 */
class MoviesListAdapter: RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>() {

    private var items: List<Movie> ?= null
    private var clickListener : ItemClickListener? = null
    private var longClickListener : ItemClickListener? = null

    fun setClicklistener(clickListener: ItemClickListener){
        this.clickListener = clickListener
    }

    fun getClickListener(): ItemClickListener? {
        return clickListener
    }

    fun setLongClicklistener(longClickListener: ItemClickListener){
        this.longClickListener = longClickListener
    }

    fun getLongClickListener(): ItemClickListener? {
        return longClickListener
    }

    fun setItems(items: List<Movie>?) {
        this.items = items
        notifyItemRangeInserted(0, items!!.size)
    }

    fun getItems(): List<Movie>?{
        return this.items
    }

    fun getItemByPosition(position: Int): Movie{
        return this.items!![position]
    }

    fun clearItems() {
        val count = itemCount
        this.items = null
        notifyItemRangeRemoved(0, count)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(items!![position])

        holder.itemView.setOnClickListener{
            if(getClickListener() != null)
                getClickListener()?.onItemClickListener(items!![position])
        }

//        holder.itemView.setOnLongClickListener {
//            if(getLongClickListener() != null){
//                getLongClickListener()?.onItemLongClickListener(items!!.[position])
//            }
//            true
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieViewHolder{
        return MovieViewHolder(LayoutInflater. from(
                parent!!.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items?.size ?:0
    }

    class MovieViewHolder internal constructor(itemView: View): RecyclerView.ViewHolder (itemView){
        var movieItem: Movie? = null

        fun bindView(movieItem: Movie?) {
            this.movieItem = movieItem

            movieItem?.title.let { itemView.title.text = movieItem?.title }
            movieItem?.overview.let { itemView.overview.text = movieItem?.overview }

            movieItem?.posterPath.let {
                Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w500/" + movieItem?.posterPath)
                        .into(itemView.imageView)
            }
        }
    }
    interface ItemClickListener {
        fun onItemClickListener(item: Movie)
        fun onItemLongClickListener(item: Movie)
    }
}