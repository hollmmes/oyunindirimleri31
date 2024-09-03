package com.tufancan.oyunindirimleri

import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.load
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.nativead.NativeAdView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class SteamFragment : Fragment() {

    lateinit var mAdview : AdView
    lateinit var mAdview2 : AdView
    lateinit var mAdview3 : AdView
    lateinit var mAdview4 : AdView
    private lateinit var database: DatabaseReference

    private var player: ExoPlayer? = null
    private lateinit var countdownTimer: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_steam, container, false)

        // Initialize ExoPlayer
        player = ExoPlayer.Builder(requireContext()).build()
        val playerView: PlayerView = view.findViewById(R.id.player_view)
        playerView.player = player

        // Make the PlayerView non-clickable
        playerView.useController = false
        playerView.isClickable = false

        // Prepare the media item
        val mediaItem = MediaItem.fromUri(Uri.parse("https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/1239690/f032ec4916fad9f6f64ecb7a9f083e835f65e362.mp4"))
        player?.setMediaItem(mediaItem)

        // Enable looping
        player?.repeatMode = ExoPlayer.REPEAT_MODE_ALL

        // Prepare the player
        player?.prepare()
        player?.playWhenReady = true

        // Set the sale title text programmatically
        val saleTitle = view.findViewById<TextView>(R.id.sale_title)
        saleTitle.text = "Çığlık Festivali İndirimi\n 28 Ekim 2024"

        // Set up the countdown timer
        countdownTimer = view.findViewById(R.id.countdown_timer)
        startCountdown()


        database = FirebaseDatabase.getInstance().getReference("images")
        // Load images into dashboard items
        val imageView1: ImageView = view.findViewById(R.id.item_image_1)
        val imageview2: ImageView = view.findViewById(R.id.item_image_2)
        val imageView3: ImageView = view.findViewById(R.id.item_image_3)
        val imageView4: ImageView = view.findViewById(R.id.item_image_4)

        // Firebase'den URL'leri çekin ve ImageView'lere yükleyin
        database.child("image1").get().addOnSuccessListener { dataSnapshot ->
            val imageUrl1 = dataSnapshot.getValue(String::class.java)
            if (!imageUrl1.isNullOrEmpty()) {
                imageView1.load(imageUrl1)
            }
        }

        database.child("image2").get().addOnSuccessListener { dataSnapshot ->
            val imageUrl2 = dataSnapshot.getValue(String::class.java)
            if (!imageUrl2.isNullOrEmpty()) {
                imageview2.load(imageUrl2)
            }
        }
        database.child("image3").get().addOnSuccessListener { dataSnapshot ->
            val imageUrl1 = dataSnapshot.getValue(String::class.java)
            if (!imageUrl1.isNullOrEmpty()) {
                imageView3.load(imageUrl1)
            }
        }

        database.child("image4").get().addOnSuccessListener { dataSnapshot ->
            val imageUrl2 = dataSnapshot.getValue(String::class.java)
            if (!imageUrl2.isNullOrEmpty()) {
                imageView4.load(imageUrl2)
            }
        }

        val ver_imageview1: ImageView = view.findViewById(R.id.vertical_item_image_1)
        val ver_imageview2: ImageView = view.findViewById(R.id.vertical_item_image_2)
        val ver_imageview3: ImageView = view.findViewById(R.id.vertical_item_image_3)
        val ver_imageview5: ImageView = view.findViewById(R.id.vertical_item_image_5)
        val ver_imageview6: ImageView = view.findViewById(R.id.vertical_item_image_6)
        val ver_imageview7: ImageView = view.findViewById(R.id.vertical_item_image_7)
        val ver_imageview8: ImageView = view.findViewById(R.id.vertical_item_image_8)
        val ver_imageview9: ImageView = view.findViewById(R.id.vertical_item_image_9)

        ver_imageview1.load("https://steamdb.info/static/img/blog/1/52a8bb8666c98fba0dcfd318e455df3698778253.png")
        ver_imageview2.load("https://steamdb.info/static/img/blog/1/ee1b420a8fa47c8795e791a05c6c0056c0e569a6.png")
        ver_imageview3.load("https://steamdb.info/static/img/blog/1/103b4210791d88a2f39532cd8983880fa1c28e51.png")
        ver_imageview5.load("https://clan.cloudflare.steamstatic.com/images/39049601/b19e87424a7474164d3f7fcc46763cfecf1ee78b.jpg")
        ver_imageview6.load("https://steamdb.info/static/img/sales/generic.webp")
        ver_imageview7.load("https://steamdb.info/static/img/blog/1/9b17026346a121af8eed4240892392182d90b6cc.png")
        ver_imageview8.load("https://steamdb.info/static/img/sales/autumn.webp")
        ver_imageview9.load("https://steamdb.info/static/img/sales/winter.webp")

        // NativeAdView nesnesini bulun
        val nativeAdView1 = view.findViewById<NativeAdView>(R.id.native_ad_view_alt1)
        val nativeAdView2 = view.findViewById<NativeAdView>(R.id.native_ad_view_alt2)
        val nativeAdView3 = view.findViewById<NativeAdView>(R.id.native_ad_view_alt3)
        val nativeAdView_yan1 = view.findViewById<NativeAdView>(R.id.native_ad_view_yan1)
        val nativeAdView_yan2 = view.findViewById<NativeAdView>(R.id.native_ad_view_yan2)
        // AdLoader ile reklamı yükleyin
        val adLoader = AdLoader.Builder(requireContext(), "ca-app-pub-4574441267168225/5135327162")
            .forNativeAd { nativeAd ->
                // Reklamın başlığını ayarlayın
                nativeAdView1.headlineView = nativeAdView1.findViewById(R.id.ad_headline_alt1)
                (nativeAdView1.headlineView as TextView).text = nativeAd.headline

                nativeAdView2.headlineView = nativeAdView2.findViewById(R.id.ad_headline_alt2)
                (nativeAdView2.headlineView as TextView).text = nativeAd.headline

                nativeAdView3.headlineView = nativeAdView3.findViewById(R.id.ad_headline_alt3)
                (nativeAdView3.headlineView as TextView).text = nativeAd.headline

                nativeAdView_yan1.headlineView = nativeAdView_yan1.findViewById(R.id.ad_headline_yan1)
                (nativeAdView_yan1.headlineView as TextView).text = nativeAd.headline

                nativeAdView_yan2.headlineView = nativeAdView_yan2.findViewById(R.id.ad_headline_yan2)
                (nativeAdView_yan2.headlineView as TextView).text = nativeAd.headline



                // Reklamın ikonunu ayarlayın
                nativeAdView1.iconView = nativeAdView1.findViewById(R.id.ad_app_icon_alt1)
                nativeAdView2.iconView = nativeAdView2.findViewById(R.id.ad_app_icon_alt2)
                nativeAdView3.iconView = nativeAdView3.findViewById(R.id.ad_app_icon_alt3)
                nativeAdView_yan1.iconView = nativeAdView_yan1.findViewById(R.id.ad_app_icon_yan1)
                nativeAdView_yan2.iconView = nativeAdView_yan2.findViewById(R.id.ad_app_icon_yan2)
                if (nativeAd.icon != null) {
                    (nativeAdView1.iconView as ImageView).setImageDrawable(nativeAd.icon!!.drawable)
                    (nativeAdView2.iconView as ImageView).setImageDrawable(nativeAd.icon!!.drawable)
                    (nativeAdView3.iconView as ImageView).setImageDrawable(nativeAd.icon!!.drawable)
                    (nativeAdView_yan1.iconView as ImageView).setImageDrawable(nativeAd.icon!!.drawable)
                    (nativeAdView_yan2.iconView as ImageView).setImageDrawable(nativeAd.icon!!.drawable)


                }

                // Reklamın açıklamasını ayarlayın
                nativeAdView1.bodyView = nativeAdView1.findViewById(R.id.ad_body_alt1)
                (nativeAdView1.bodyView as TextView).text = nativeAd.body

                nativeAdView2.bodyView = nativeAdView2.findViewById(R.id.ad_body_alt2)
                (nativeAdView2.bodyView as TextView).text = nativeAd.body

                nativeAdView3.bodyView = nativeAdView3.findViewById(R.id.ad_body_alt3)
                (nativeAdView3.bodyView as TextView).text = nativeAd.body







                // Native reklamı yerleştirin
                nativeAdView1.setNativeAd(nativeAd)
                nativeAdView2.setNativeAd(nativeAd)
                nativeAdView3.setNativeAd(nativeAd)
                nativeAdView_yan1.setNativeAd(nativeAd)
                nativeAdView_yan2.setNativeAd(nativeAd)




            }
            .build()
        adLoader.loadAd(AdRequest.Builder().build())



        mAdview = view.findViewById(R.id.adView)
        mAdview2 = view.findViewById(R.id.adView1)
        mAdview3 = view.findViewById(R.id.adView2)
        mAdview4 = view.findViewById(R.id.adView3)


        val adRequest = AdRequest.Builder().build()
        mAdview.loadAd(adRequest)
        mAdview2.loadAd(adRequest)
        mAdview3.loadAd(adRequest)
        mAdview4.loadAd(adRequest)


        return view
    }

    private fun startCountdown() {
        // Set the target date to 27 November 2024
        val targetDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            .parse("2024-10-29 19:00:00")?.time ?: return

        val currentTime = System.currentTimeMillis()
        val timeDifference = targetDate - currentTime

        object : CountDownTimer(timeDifference, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
                val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 24
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60
                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60

                countdownTimer.text = String.format(Locale.getDefault(), "%02d:%02d:%02d:%02d",
                    days, hours, minutes, seconds)
            }

            override fun onFinish() {
                countdownTimer.text = "00:00:00:00"
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Release the player when the view is destroyed
        player?.release()
        player = null
    }
}
