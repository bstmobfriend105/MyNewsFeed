package com.test.newsfeed.utils

import android.content.Context
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import com.test.newsfeed.R
import com.test.newsfeed.apis.Model
import kotlinx.android.synthetic.main.feedlist_item.view.*
import android.widget.ImageView
import android.os.AsyncTask
import android.graphics.Bitmap
import java.net.URL
import android.graphics.BitmapFactory

class FeedAdapter(private val context: Context,
                    private val dataSource: List<Model.Article>) : BaseAdapter() {

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
        rowView.newsTitle.text = dataSource[position].title
        DownLoadImageTask(rowView.newsImage)
            .execute(dataSource[position].urlToImage)

        return rowView
    }
}

private class DownLoadImageTask(internal val imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
    override fun doInBackground(vararg urls: String): Bitmap? {
        val urlOfImage = urls[0]
        return try {
            val inputStream = URL(urlOfImage).openStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    override fun onPostExecute(result: Bitmap?) {
        if(result!=null){
            imageView.setImageBitmap(result)
        }
    }
}