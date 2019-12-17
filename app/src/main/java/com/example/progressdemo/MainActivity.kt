package com.example.progressdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_item.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        show_dialog.setOnClickListener {
            val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.dialog_item, null)
            showView(view)
            AlertDialog
                .Builder(this@MainActivity)
                .setView(view)
                .setPositiveButton("OK") { _, _ ->
                }
                .show()
        }
        start_progress.setOnClickListener {
            showView(window.decorView)
        }
    }
    private fun showView(view : View){
        view.progress.visibility = VISIBLE
        Thread {
            for (i in 1..100) {
                Thread.sleep(100)
                runOnUiThread {
                    view.progress_msg.text = "$i%"
                    view.progress.progress = i
                }
            }
            runOnUiThread{
                view.progress_msg.text = "done."
                view.progress.visibility = GONE
            }
        }.start()
    }
}
