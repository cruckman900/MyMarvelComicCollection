package com.cruckman900.mymarvelcomiccollection.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cruckman900.mymarvelcomiccollection.R
import com.cruckman900.mymarvelcomiccollection.model.CollectionEntity
import kotlinx.android.synthetic.main.api_item_layout.view.iv_thumbnail
import kotlinx.android.synthetic.main.db_item_layout.view.*

class CollectionAdapter(
    var dataSet: List<CollectionEntity>
): RecyclerView.Adapter<CollectionAdapter.CollectionHolder>() {

    class CollectionHolder(val collectionView: View): RecyclerView.ViewHolder(collectionView) {
        fun onBind(dataItem: CollectionEntity) {
            Glide.with(collectionView.context).load(dataItem.imageURL)
                .into(collectionView.iv_db_thumbnail)
            collectionView.tv_db_title.text = dataItem.title
            collectionView.tv_db_year.text = dataItem.year.toString()
            collectionView.tv_db_issue_number.text = dataItem.issueNumber.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionHolder {
        return CollectionHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.db_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CollectionHolder, position: Int) {
        holder.onBind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setCollectionData(newData: List<CollectionEntity>) {
        this.dataSet = newData
        notifyDataSetChanged()
    }
}