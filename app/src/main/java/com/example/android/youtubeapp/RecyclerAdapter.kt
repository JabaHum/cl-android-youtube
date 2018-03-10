package com.example.android.youtubeapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.util.*

class RecyclerAdapter(listener:OnItemClickedListener) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var list: List<Resource> = ArrayList()
    var mListener:OnItemClickedListener = listener

    fun setItems(roomsList: List<Resource>) {
        this.list = roomsList
        notifyDataSetChanged()
    }

    interface OnItemClickedListener {
        fun onItemClicked(selectedVideo:Resource)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = 2

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imageView:ImageView = itemView.findViewById(R.id.image_view)
        private var textView: TextView = itemView.findViewById(R.id.text_view)

        fun bind(resource: Resource) = with(itemView) {
            Picasso.with(itemView.context).load(resource.url.replace(".mp4",".png")).into(imageView)
            textView.text = resource.publicId
            itemView.setOnClickListener {
                mListener.onItemClicked(resource)
            }
        }

    }
}