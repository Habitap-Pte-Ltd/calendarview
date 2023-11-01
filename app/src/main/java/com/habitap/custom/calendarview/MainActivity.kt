package com.habitap.custom.calendarview

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val CATEGORY_SAMPLE = "com.habitap.custom.calendarview.SAMPLE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = findViewById<RecyclerView>(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = ResolveInfoAdapter(this, getAllSampleActivities())
    }

    private fun getAllSampleActivities(): List<ResolveInfo> {
        val filter = Intent()
        filter.action = Intent.ACTION_RUN
        filter.addCategory(CATEGORY_SAMPLE)
        return packageManager.queryIntentActivities(filter, 0)
    }

    internal class ResolveInfoAdapter(val context: Context, private val samples: List<ResolveInfo>) :
        RecyclerView.Adapter<ResolveInfoAdapter.ResolveInfoViewHolder>() {
        private val pm: PackageManager
        private val inflater: LayoutInflater

        init {
            inflater = LayoutInflater.from(context)
            pm = context.packageManager
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ResolveInfoViewHolder {
            val view: View = inflater.inflate(R.layout.item_route, viewGroup, false)
            return ResolveInfoViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ResolveInfoViewHolder, i: Int) {
            val item = samples[i]
            viewHolder.textView.text = item.loadLabel(pm)
        }

        override fun getItemCount(): Int {
            return samples.size
        }

        private fun onRouteClicked(route: ResolveInfo) {
            val activity = route.activityInfo
            val name = ComponentName(activity.applicationInfo.packageName, activity.name)
            context.startActivity(Intent(Intent.ACTION_VIEW).setComponent(name))
        }

        internal inner class ResolveInfoViewHolder(view: View) :
            RecyclerView.ViewHolder(view), View.OnClickListener {
            val textView: TextView

            init {
                textView = view.findViewById(android.R.id.text1)
                view.setOnClickListener(this)
            }

            override fun onClick(v: View) {
                onRouteClicked(samples[adapterPosition])
            }
        }
    }
}