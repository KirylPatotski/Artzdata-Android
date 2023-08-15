package de.arztdata.app.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.arztdata.app.InfoFragment
import de.arztdata.app.MainActivity
import de.arztdata.app.R
import de.arztdata.app.data.SubcategoryItem
import de.arztdata.app.ui.SubcategoryAdapter.SubcategoryViewHolder

class SubcategoryAdapter(private val subcategories: List<SubcategoryItem>, private val activity: Activity) : RecyclerView.Adapter<SubcategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subcategory, parent, false)
        return SubcategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
        val (_, name) = subcategories[position]
        holder.subcategoryNameTextView.text = name
        println()
        holder.itemView.setOnClickListener { v: View? ->


            (activity as MainActivity).openFragment(InfoFragment(subcategories[position].entry))

        }
    }

    override fun getItemCount(): Int {
        return subcategories.size
    }

    class SubcategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var subcategoryNameTextView: TextView
        init { subcategoryNameTextView = itemView.findViewById(R.id.subcategoryNameTextView) }
    }
}