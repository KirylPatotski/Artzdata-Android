package de.arztdata.app.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import de.arztdata.app.R
import de.arztdata.app.data.EntryItem


class EntryAdapter(private val entries: List<EntryItem>?, private val activity: Activity) : RecyclerView.Adapter<EntryAdapter.EntryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return EntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {

        var entry = entries!![position]



        holder.entryImageView.load(Uri.parse(entry.imageUrl))
        println(entry.imageUrl)
        holder.nameTextView.text = entry.name

        holder.addressTextView.text = entry.addressContact.address
        holder.phoneTextView.text = entry.addressContact.phoneNumber
        holder.emailTextView.text = entry.addressContact.email

        holder.jobTextView.text = entry.job + "\n\n" + entry.description
        holder.websiteTextView.text = entry.link
        holder.youtubeTextView.text = entry.youtubeVideoLink

        holder.youtubeTextView.setOnClickListener { openLink(entry.youtubeVideoLink) }
        holder.websiteTextView.setOnClickListener { openLink(entry.link) }
        holder.emailTextView.setOnClickListener { openEmail(entry.addressContact.email) }
        holder.phoneTextView.setOnClickListener { dial(entry.addressContact.phoneNumber) }

        println()
    }

    fun dial(number: String){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$number")
        activity.startActivity(intent)
    }

    fun openEmail(mail: String) {
        val emailText = ""

        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        emailIntent.type = "vnd.android.cursor.item/email"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "")
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailText)
        activity.startActivity(Intent.createChooser(emailIntent, ""))
    }

    fun openLink(url: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity.startActivity(browserIntent)
    }

    override fun getItemCount(): Int {
        return entries!!.size
    }

    class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val entryImageView: ImageView = itemView.findViewById(R.id.entryImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val jobTextView: TextView = itemView.findViewById(R.id.job)
        val addressTextView: TextView = itemView.findViewById(R.id.address)
        val phoneTextView: TextView = itemView.findViewById(R.id.phone)
        val emailTextView: TextView = itemView.findViewById(R.id.email)
        val youtubeTextView: TextView = itemView.findViewById(R.id.youtube)
        val websiteTextView: TextView = itemView.findViewById(R.id.website)
    }
}