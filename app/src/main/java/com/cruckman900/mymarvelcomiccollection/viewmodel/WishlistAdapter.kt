package com.cruckman900.mymarvelcomiccollection.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cruckman900.mymarvelcomiccollection.R
import com.cruckman900.mymarvelcomiccollection.model.WishlistEntity
import kotlinx.android.synthetic.main.db_item_layout.view.*

class WishlistAdapter(
    var dataSet: List<WishlistEntity>
): RecyclerView.Adapter<WishlistAdapter.WishlistHolder>() {
    class WishlistHolder(val wishlistView: View): RecyclerView.ViewHolder(wishlistView) {
        fun onBind(dataItem: WishlistEntity) {
            Glide.with(wishlistView.context).load(dataItem.imageURL)
                .into(wishlistView.iv_db_thumbnail)
            wishlistView.tv_db_title.text = dataItem.title
            wishlistView.tv_db_year.text = dataItem.year.toString()
            wishlistView.tv_db_issue_number.text = dataItem.issueNumber.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistHolder {
        return WishlistHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.db_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WishlistHolder, position: Int) {
        holder.onBind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setWishlistData(newData: List<WishlistEntity>) {
        this.dataSet = newData
        notifyDataSetChanged()
    }
}