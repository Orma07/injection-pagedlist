package com.example.myapplication.views

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.domain.Repository
import kotlinx.android.synthetic.main.view_cell.view.*
import android.view.LayoutInflater
import com.squareup.picasso.Picasso
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.net.URL


class RepoViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    var repoNameTextView : TextView = view.repoName
    var ownerTextView : TextView = view.ownerTextView
    var image : ImageView = view.ownerImageView
}


class RepoAdapter(var context:Context) : PagedListAdapter<Repository, RepoViewHolder>(DIFF_CALLBACK) {


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(p0: Repository, p1: Repository): Boolean {
                return  (p0.name == p1.name && p1.owner!!.login == p0.owner!!.login)
            }

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return  oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RepoViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(
            com.example.myapplication.R.layout.view_cell,
            p0, false
        )
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RepoViewHolder, p1: Int) {
        val current = getItem(p1)
        viewHolder.ownerTextView.text = current?.owner?.login
        viewHolder.repoNameTextView.text = current?.name
        if(current?.owner?.avatar_url!=null && !current.owner!!.avatar_url!!.isEmpty() ) {
            Picasso.with(context)
                .load(current.owner!!.avatar_url)
                .centerCrop()
                .fit()
                .into(viewHolder.image)
        }

    }

}