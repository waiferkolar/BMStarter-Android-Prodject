package coder.seventy.two

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import coder.seventy.two.adapers.AlbumAdapter
import coder.seventy.two.modals.Album
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album_detail.*
import org.jetbrains.anko.toast

class AlbumDetail : AppCompatActivity() {
    lateinit var mAdView: AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)
        supportActionBar?.title = "Album Detail"

        MobileAds.initialize(this, getString(R.string.admob_app_id))

        mAdView = findViewById(R.id.adSecondView)

        val adRequest = AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build()
        mAdView.loadAd(adRequest)

        val album = intent.getParcelableExtra<Album>(AlbumAdapter.ALBUM_KEY)

        now_tvTItle.text = album.title
        now_tvAlbumId.text = album.albumId.toString()
        now_uuid.text = album.id.toString()
        now_thumbnail.text = album.thumbnailUrl

        Picasso.get().load(album.url).into(now_albumImage)
    }
}
