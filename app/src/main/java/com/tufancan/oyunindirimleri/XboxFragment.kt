package com.tufancan.oyunindirimleri

import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.load
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.nativead.NativeAdView
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class XboxFragment : Fragment() {

    lateinit var mAdview : AdView
    lateinit var mAdview2 : AdView
    lateinit var mAdview3 : AdView
    lateinit var mAdview4 : AdView
    private var player: ExoPlayer? = null
    private lateinit var countdownTimer: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_xbox, container, false)

        // Initialize ExoPlayer
        player = ExoPlayer.Builder(requireContext()).build()
        val playerView: PlayerView = view.findViewById(R.id.player_view)
        playerView.player = player

        // Make the PlayerView non-clickable
        playerView.useController = false
        playerView.isClickable = false

        // Prepare the media item
        val mediaItem = MediaItem.fromUri(Uri.parse("https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/1037910/6211b7c86e1c261efaff1c7f0a4a278f0e1a6ac8.mp4"))
        player?.setMediaItem(mediaItem)

        // Enable looping
        player?.repeatMode = ExoPlayer.REPEAT_MODE_ALL

        // Prepare the player
        player?.prepare()
        player?.playWhenReady = true

        // Set the sale title text programmatically
        val saleTitle = view.findViewById<TextView>(R.id.sale_title)
        saleTitle.text = "Yeni Yıl İndirimi\n 15 Aralık – 5 Ocak 2025"

        // Set up the countdown timer
        countdownTimer = view.findViewById(R.id.countdown_timer)
        startCountdown()

        // Load images into dashboard items
        val imageView1: ImageView = view.findViewById(R.id.item_image_1)
        val imageView3: ImageView = view.findViewById(R.id.item_image_3)
        val imageView5: ImageView = view.findViewById(R.id.item_image_5)


        imageView1.load("https://assets.xboxservices.com/assets/61/06/6106552d-d867-42b9-8ec7-7f5fc3a2fd05.jpg?n=FY25UGS-ROW-Tile-1136x639-02.jpg")
        imageView3.load("https://assets.xboxservices.com/assets/61/06/6106552d-d867-42b9-8ec7-7f5fc3a2fd05.jpg?n=FY25UGS-ROW-Tile-1136x639-02.jpg")
        imageView5.load("https://assets.xboxservices.com/assets/61/06/6106552d-d867-42b9-8ec7-7f5fc3a2fd05.jpg?n=FY25UGS-ROW-Tile-1136x639-02.jpg")


        val ver_imageview1: ImageView = view.findViewById(R.id.vertical_item_image_1)
        val ver_imageview2: ImageView = view.findViewById(R.id.vertical_item_image_2)
        val ver_imageview3: ImageView = view.findViewById(R.id.vertical_item_image_3)
        val ver_imageview5: ImageView = view.findViewById(R.id.vertical_item_image_5)

        ver_imageview1.load("https://static0.gamerantimages.com/wordpress/wp-content/uploads/2022/10/Xbox-Shocktober.jpg")
        ver_imageview2.load("https://www.merlininkazani.com/images/games/6793/114500_640.jpg")
        ver_imageview3.load("https://static1.thegamerimages.com/wordpress/wp-content/uploads/2021/11/the-game-awards-xbox.jpg")
        ver_imageview5.load("https://ukstories.microsoft.com/wp-content/uploads/2022/11/Xbox-holiday-image.jpeg")
        // NativeAdView nesnesini bulun
        val nativeAdView1 = view.findViewById<NativeAdView>(R.id.native_ad_view_alt1)
        val nativeAdView_yan1 = view.findViewById<NativeAdView>(R.id.native_ad_view_yan1)
        val nativeAdView_yan2 = view.findViewById<NativeAdView>(R.id.native_ad_view_yan2)
        // AdLoader ile reklamı yükleyin
        val adLoader = AdLoader.Builder(requireContext(), "ca-app-pub-4574441267168225/5135327162")
            .forNativeAd { nativeAd ->
                // Reklamın başlığını ayarlayın
                nativeAdView1.headlineView = nativeAdView1.findViewById(R.id.ad_headline_alt1)
                (nativeAdView1.headlineView as TextView).text = nativeAd.headline


                nativeAdView_yan1.headlineView = nativeAdView_yan1.findViewById(R.id.ad_headline_yan1)
                (nativeAdView_yan1.headlineView as TextView).text = nativeAd.headline

                nativeAdView_yan2.headlineView = nativeAdView_yan2.findViewById(R.id.ad_headline_yan2)
                (nativeAdView_yan2.headlineView as TextView).text = nativeAd.headline



                // Reklamın ikonunu ayarlayın
                nativeAdView1.iconView = nativeAdView1.findViewById(R.id.ad_app_icon_alt1)
                nativeAdView_yan1.iconView = nativeAdView_yan1.findViewById(R.id.ad_app_icon_yan1)
                nativeAdView_yan2.iconView = nativeAdView_yan2.findViewById(R.id.ad_app_icon_yan2)
                if (nativeAd.icon != null) {
                    (nativeAdView1.iconView as ImageView).setImageDrawable(nativeAd.icon!!.drawable)
                    (nativeAdView_yan1.iconView as ImageView).setImageDrawable(nativeAd.icon!!.drawable)
                    (nativeAdView_yan2.iconView as ImageView).setImageDrawable(nativeAd.icon!!.drawable)


                }

                // Reklamın açıklamasını ayarlayın
                nativeAdView1.bodyView = nativeAdView1.findViewById(R.id.ad_body_alt1)
                (nativeAdView1.bodyView as TextView).text = nativeAd.body








                // Native reklamı yerleştirin
                nativeAdView1.setNativeAd(nativeAd)
                nativeAdView_yan1.setNativeAd(nativeAd)
                nativeAdView_yan2.setNativeAd(nativeAd)




            }
            .build()
        adLoader.loadAd(AdRequest.Builder().build())



        mAdview = view.findViewById(R.id.adView)

        val adRequest = AdRequest.Builder().build()
        mAdview.loadAd(adRequest)

        return view
    }

    private fun startCountdown() {
        // Set the target date to 27 November 2024
        val targetDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            .parse("2024-12-15 03:00:00")?.time ?: return

        val currentTime = System.currentTimeMillis()
        val timeDifference = targetDate - currentTime

        object : CountDownTimer(timeDifference, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
                val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 24
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60
                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60

                countdownTimer.text = String.format(
                    Locale.getDefault(), "%02d:%02d:%02d:%02d",
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