package com.example.newsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.newsapp.R
import com.example.newsapp.databinding.NewsCardBinding
import com.example.newsapp.presentation.uistate.NewsItemUIState

class NewsListAdapter(val onItemClicked: (NewsItemUIState) -> Unit): RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    private var data: List<NewsItemUIState> = emptyList()
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(newsItemUIState: NewsItemUIState) {
            val bind = NewsCardBinding.bind(itemView)
            itemView.setOnClickListener {
                onItemClicked(newsItemUIState)
            }
            bind.apply {
                newsHeadline.text = newsItemUIState.title
                Glide.with(newsImage)
                    .asBitmap()
                    .load(newsItemUIState.image)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(BitmapImageViewTarget(newsImage))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
    fun setData(newsList: List<NewsItemUIState>) {
        this.data = newsList
    }
}