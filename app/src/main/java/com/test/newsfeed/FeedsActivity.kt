package com.test.newsfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import com.test.newsfeed.utils.FeedAdapter
import kotlinx.android.synthetic.main.activity_feeds.*
import com.test.newsfeed.unit.Feed
import kotlinx.android.synthetic.main.dialog_accesslocation.view.*
import android.content.SharedPreferences

class FeedsActivity : AppCompatActivity()  {
    private val DONT_ASK = "DONT_ASK"
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_feeds)
        locationSegment.setLocation(1)

        sharedPref = getSharedPreferences(DONT_ASK, 0)
        if (!sharedPref.getBoolean(DONT_ASK, false)) {
            showAccessLocationDialog()
        } else {
            loadFeedsList()
        }

        btnLogout.setOnClickListener {
            onBackPressed()
        }
    }

    private fun loadFeedsList() {
        var feedList = arrayListOf<Feed>()
        for (id in 0 until 10) {
            val feedItem = Feed()
            feedItem.newsTitle = "News Title"
            feedItem.newsBody = "News source"
            feedItem.newsTime = "News time"
            feedList.add(feedItem)
        }

        val adapter = FeedAdapter(this, feedList)
        listViewFeeds.setAdapter(adapter)
    }

    private fun showAccessLocationDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_accesslocation, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)

        val alertDialog = builder.show()
        dialogView.checkboxDontAsk.setOnClickListener {
            if (it is CheckBox) {
                val checked: Boolean = it.isChecked
                val editor = sharedPref.edit()
                editor.putBoolean(DONT_ASK, checked)
                editor.apply()
            }
        }

        dialogView.btnDeny.setOnClickListener {

            alertDialog.dismiss()
            showWarningLocationDialog()
        }

        dialogView.btnAllow.setOnClickListener {
            alertDialog.dismiss()
            loadFeedsList()
        }
    }

    private fun showWarningLocationDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_warninglocation, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)

        val alertDialog = builder.show()
        dialogView.btnAllow.setOnClickListener {
            alertDialog.dismiss()
            loadFeedsList()
        }
    }

}