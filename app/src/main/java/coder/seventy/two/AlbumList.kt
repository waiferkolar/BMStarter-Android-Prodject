package coder.seventy.two

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import coder.seventy.two.adapers.AlbumAdapter
import coder.seventy.two.modals.Album
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_album_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

class AlbumList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list)

        supportActionBar?.title = "Alubm List"

        doAsync {
            val jsons = URL("https://jsonplaceholder.typicode.com/photos").readText()

            uiThread {

                val albums = Gson().fromJson(jsons, Array<Album>::class.java).toList()

                toast("There are ${albums.size}")

                val layoutManger = GridLayoutManager(this@AlbumList, 2)
                albumRecyler.layoutManager = layoutManger
                val adapter  = AlbumAdapter(this@AlbumList,albums)
                albumRecyler.adapter = adapter
            }
        }
    }
}
