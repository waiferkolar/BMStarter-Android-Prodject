package coder.seventy.two

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    lateinit var mAdView: AdView
    lateinit var bounceAnim: Animation
    lateinit var zoomAnim: Animation
    lateinit var flipAnim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        MobileAds.initialize(this, getString(R.string.admob_app_id))

        mAdView = findViewById(R.id.adView)
//        val adRequest = AdRequest.Builder()
//            .addTestDevice("73AD1C1BA98B2C0B00BDC5A543299B76").build()
//        val adRequest = AdRequest.Builder()
//            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build()
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        bounceAnim = AnimationUtils.loadAnimation(this, R.anim.bounce)
        zoomAnim = AnimationUtils.loadAnimation(this, R.anim.zoom)
        flipAnim = AnimationUtils.loadAnimation(this, R.anim.flip)
        appLogo.animation = bounceAnim
        bounceAnim.setAnimationListener(this)
        zoomAnim.setAnimationListener(this)

    }

    override fun onAnimationRepeat(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) {
        if (animation == bounceAnim) {
            tvSlogan.visibility = View.VISIBLE
            tvSlogan.animation = zoomAnim
        } else if (animation == zoomAnim) {
            if (checkConn()) {
                startButton.visibility = View.VISIBLE
                startButton.animation = flipAnim

                startButton.setOnClickListener {
                    val intent = Intent(this@MainActivity,AlbumList::class.java)
                    startActivity(intent)
                }
            } else {
                alert(
                    "You device need Internet Connectin to user our App",
                    "Connection Problem"
                ) {
                    yesButton { toast("Ok Try Again") }
                    noButton { toast("U can't user our App") }
                }.show()
                progressBar.visibility = View.VISIBLE
            }
        }
    }


    override fun onAnimationStart(animation: Animation?) {
    }

    fun checkConn(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}
