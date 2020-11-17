package com.cruckman900.mymarvelcomiccollection.viewmodel

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cruckman900.mymarvelcomiccollection.R
import com.cruckman900.mymarvelcomiccollection.model.Results
import kotlinx.android.synthetic.main.api_item_layout.view.*

private const val TAG = "ComicAdapter"

class ComicAdapter(
    val dataSet: List<Results>,
    val collectionListener: (dataItem: Results) -> Unit,
    val wishlistListener: (dataItem: Results) -> Unit
) : RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    class ComicViewHolder(val comicView: View): RecyclerView.ViewHolder(comicView) {
        fun onBind(
            dataItem: Results,
            collectionListener: (dataItem: Results) -> Unit,
            wishlistListener: (dataItem: Results) -> Unit
        ) {
            Log.d(TAG, "onBind: what's going on? ${dataItem.thumbnail.path}.${dataItem.thumbnail.extension}")
            Glide.with(comicView.context).load("${dataItem.thumbnail.path}.${dataItem.thumbnail.extension}")
                    .into(comicView.iv_thumbnail)
            comicView.tv_title.text = dataItem.title.split(" (")[0]
            val issueNumber = when (dataItem.issueNumber.toInt()) {
                0 -> ""
                else -> "Issue #${dataItem.issueNumber.toInt().toString()}"
            }
            comicView.tv_year.text = dataItem.dates[0].date.split("-")[0]
            comicView.tv_issue_number.text = issueNumber

            comicView.btn_add_to_collection.setOnClickListener {
                collectionListener.invoke(dataItem)
            }

            comicView.btn_add_to_wishlist.setOnClickListener {
                wishlistListener.invoke(dataItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.api_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.onBind(dataSet[position], collectionListener, wishlistListener)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: ${dataSet.size}")
        return dataSet.size
    }
}