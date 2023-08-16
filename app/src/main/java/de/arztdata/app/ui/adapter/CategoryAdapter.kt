package de.arztdata.app.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.arztdata.app.R
import de.arztdata.app.data.CategoryItem
import de.arztdata.app.ui.adapter.CategoryAdapter.CategoryViewHolder

class CategoryAdapter(private val categories: List<CategoryItem>, private val activity: Activity) : RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryNameTextView.text = category.name
        holder.subcategoryRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.subcategoryRecyclerView.adapter = category.subcategory?.let { SubcategoryAdapter(it,activity) }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryNameTextView: TextView
        var subcategoryRecyclerView: RecyclerView

        init {
            categoryNameTextView = itemView.findViewById(R.id.categoryNameTextView)
            subcategoryRecyclerView = itemView.findViewById(R.id.subcategoryRecyclerView)
        }
    }
}