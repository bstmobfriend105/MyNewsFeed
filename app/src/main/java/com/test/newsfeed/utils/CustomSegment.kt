package com.test.newsfeed.utils

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.test.newsfeed.R

class CustomSegment : LinearLayout, View.OnClickListener {

    object location {
        val YOUR_LOCATION = 1
        val INTERNATIONAL = 2
        val LOCAL = 3
    }

    private var yourLocation : AppCompatButton? = null
    private var international : AppCompatButton? = null
    private var local : AppCompatButton? = null

    var locationPosition = 2

    var locationPositionChangeListener : OnLocationPositionChangeListener? = null
        set(value) {field = value}

    constructor(context: Context) : this(context, null) {
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {

        inflate(context, R.layout.custom_segment, this)

        orientation = HORIZONTAL
        background = ContextCompat.getDrawable(context, R.drawable.rounded_border)

        yourLocation = findViewById(R.id.yourLocation) as AppCompatButton
        international = findViewById(R.id.international) as AppCompatButton
        local = findViewById(R.id.local) as AppCompatButton

        yourLocation?.setOnClickListener(this)
        international?.setOnClickListener(this)
        local?.setOnClickListener(this)
    }

    private fun clearViews() {

        yourLocation?.setBackgroundColor(Color.TRANSPARENT)
        international?.setBackgroundColor(Color.TRANSPARENT)
        local?.setBackgroundColor(Color.TRANSPARENT)

        yourLocation?.setTextColor(resources.getColor(R.color.color_grey))
        international?.setTextColor(resources.getColor(R.color.color_grey))
        local?.setTextColor(resources.getColor(R.color.color_grey))

        yourLocation?.isClickable = true
        international?.isClickable = true
        local?.isClickable = true
    }

    private fun paintViews(position : Int, button : AppCompatButton) {

        button.isClickable = false
        button.setTextColor(Color.WHITE)

        locationPosition = position
        locationPositionChangeListener?.onLocationPositionChanged(position)

        when(position) {
            location.YOUR_LOCATION -> button.setBackgroundResource(
                R.drawable.rounded_border_btn_left
            )
            location.INTERNATIONAL -> button.setBackgroundResource(
                R.drawable.normal_btn
            )
            location.LOCAL -> button.setBackgroundResource(R.drawable.rounded_border_btn_right)
        }
    }

    override fun onClick(v: View?) {

        clearViews()

        when(v?.id) {
            R.id.yourLocation -> paintViews(location.YOUR_LOCATION, v as AppCompatButton)
            R.id.international -> paintViews(location.INTERNATIONAL, v as AppCompatButton)
            R.id.local -> paintViews(location.LOCAL, v as AppCompatButton)
        }
    }

    fun setLocation(position : Int) {

        when(position) {

            location.YOUR_LOCATION -> paintViews(location.YOUR_LOCATION, yourLocation as AppCompatButton)
            location.INTERNATIONAL -> paintViews(location.INTERNATIONAL, international as AppCompatButton)
            location.LOCAL -> paintViews(location.LOCAL, local as AppCompatButton)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, resources.getDimensionPixelSize(R.dimen.custom_segement_height))
    }

    interface OnLocationPositionChangeListener {
        fun onLocationPositionChanged(locationPosition: Int)
    }
}
