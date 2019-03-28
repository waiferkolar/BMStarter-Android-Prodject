package coder.seventy.two.adapers

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coder.seventy.two.AlbumDetail
import coder.seventy.two.R
import coder.seventy.two.modals.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_row.view.*

class AlbumAdapter(val context: Context, val albums: List<Album>) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    companion object {
        val ALBUM_KEY = "ALBUM_KEY"
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.album_row, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        val album = albums[p1]

        holder.itemView.tvTitle.text = album.title.substring(0,10)
        Picasso.get().load(album.thumbnailUrl).into(holder.itemView.albumImg)
        // https://via.placeholder official po
        // [https,via.placeholder official po]
        // via.placeholder official po
        // placeholder official po
        // 10
        val ary : List<String> = album.url.split("://")
        val second : List<String> = ary[1].split(".")
        val third : List<String> = second[1].split(" ")
        holder.itemView.tvDetail.text = third[0]

        holder.itemView.btnShow.setOnClickListener {
            val intent = Intent(context, AlbumDetail::class.java)
            intent.putExtra(ALBUM_KEY, album)
            context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}