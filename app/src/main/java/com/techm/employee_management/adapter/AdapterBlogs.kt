package com.techm.employee_management.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.techm.employee_management.R
import com.techm.employee_management.model.ModelEmployeeInformation
import kotlinx.android.synthetic.main.item_layout.view.*


class AdapterBlog(private var items: ArrayList<ModelEmployeeInformation>, private val context: Context?) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of blog in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false))
    }

    // Binds each object in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.authorName.text = items[position].employee_name

            /*Glide.with(context)
                .load(items[position].)
                .placeholder(R.drawable.no_image)
                .into(holder.imageBlog)
*/
    }
    fun setList(dataInformation: ArrayList<ModelEmployeeInformation>)
    {
        this.items=dataInformation
        notifyDataSetChanged()
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

   /* val authorName: TextView = view.authorName
    val imageBlog:ImageView=view.imageBlog*/
}