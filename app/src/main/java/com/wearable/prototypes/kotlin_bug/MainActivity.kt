package com.wearable.prototypes.kotlin_bug

import android.graphics.RectF
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.text

const val TAG = "MainActivity"

class MainActivity : WearableActivity() {

  private lateinit var gestureDetector: GestureDetector

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
      override fun onDown(e: MotionEvent): Boolean {
        Log.d(TAG, "onDown ${e.x}, ${e.y} ")
        return true
      }

      override fun onSingleTapUp(e: MotionEvent): Boolean {
        val rect = RectF(0f, 0f, 60f, 60f)
        rect.offset(e.x, e.y)
        Log.d(TAG, "tap source ${e.x}, ${e.y} = ${rect.centerX()}, ${rect.centerY()} ")
        return true
      }
    })

    text.setOnTouchListener { v, event -> gestureDetector.onTouchEvent(event) }


    // Enables Always-on
    setAmbientEnabled()
  }
}