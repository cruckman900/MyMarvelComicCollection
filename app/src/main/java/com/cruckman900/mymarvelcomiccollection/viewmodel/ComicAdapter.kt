package com.cruckman900.mymarvelcomiccollection.viewmodel

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cruckman900.mymarvelcomiccollection.R
import com.cruckman900.mymarvelcomiccollection.model.ComicResponse
import com.cruckman900.mymarvelcomiccollection.model.Results
import kotlinx.android.synthetic.main.api_item_layout.view.*

private const val TAG = "ComicAdapter"

class ComicAdapter(val dataSet: List<Results>) :
        RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    class ComicViewHolder(val comicView: View): RecyclerView.ViewHolder(comicView) {
        fun onBind(dataItem: Results) {
            Log.d(TAG, "onBind: what's going on? ${dataItem.thumbnail.path}.${dataItem.thumbnail.extension}")
            Glide.with(comicView.context).load("${dataItem.thumbnail.path}.${dataItem.thumbnail.extension}")
                    .into(comicView.iv_thumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.api_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.onBind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}