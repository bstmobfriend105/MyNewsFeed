package com.test.newsfeed.utils

import android.content.Context
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import com.test.newsfeed.R
import kotlinx.android.synthetic.main.feedlist_item.view.*
import com.test.newsfeed.unit.Feed

class FeedAdapter(private val context: Context,
                    private val dataSource: ArrayList<Feed>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.feedlist_item, parent, false)
        rowView.newsTitle.text = dataSource[position].newsTitle
        rowView.newsBody.text = dataSource[position].newsBody + " - " + dataSource[position].newsTime

        return rowView
    }
}