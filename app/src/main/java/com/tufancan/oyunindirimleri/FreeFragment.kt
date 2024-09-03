package com.tufancan.oyunindirimleri

import android.net.Uri
import android.os.Bundle
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


class FreeFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_free, container, false)

        // Initialize ExoPlayer
        player = ExoPlayer.Builder(requireContext()).build()
        val playerView: PlayerView = view.findViewById(R.id.player_view)
        playerView.player = player

        // Make the PlayerView non-clickable
        playerView.useController = false
        playerView.isClickable = false

        // Prepare the media item
        val mediaItem = MediaItem.fromUri(Uri.parse("https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/1526200/4f2c45d70c916f300cf842d7fb146d7e05bd212e.mp4"))
        player?.setMediaItem(mediaItem)

        // Enable looping
        player?.repeatMode = ExoPlayer.REPEAT_MODE_ALL

        // Prepare the player
        player?.prepare()
        player?.playWhenReady = true

        // Set the sale title text programmatically
        val saleTitle = view.findViewById<TextView>(R.id.sale_title)
        saleTitle.text = "Her gün yenilenen bedava oyunlara kolaylıkla aşağıdan ulaşabilirsiniz"

        // Set up the countdown time

        // Load images into dashboard items





        val vertical_item_image_cekilis1: ImageView = view.findViewById(R.id.vertical_item_image_cekilis1)
        val vertical_item_image_cekisil2: ImageView = view.findViewById(R.id.vertical_item_image_cekilis2)

        vertical_item_image_cekilis1.load("https://cdn-ext.fanatical.com/production/product/1280x720/fd51a206-6b70-4f80-a37a-284dc5070978.jpg")
        vertical_item_image_cekisil2.load("https://www.operationsports.com/wp-content/uploads/2023/08/wild-card-football.png?fit=1200%2C620")

        // NativeAdView nesnesini bulun
        val nativeAdView1 = view.findViewById<NativeAdView>(R.id.native_ad_view_alt1)
        val nativeAdView2 = view.findViewById<NativeAdView>(R.id.native_ad_view_alt2)
        // AdLoader ile reklamı yükleyin
        val adLoader = AdLoader.Builder(requireContext(), "ca-app-pub-3940256099942544/2247696110")
            .forNativeAd { nativeAd ->
                // Reklamın başlığını ayarlayın
                nativeAdView1.headlineView = nativeAdView1.findViewById(R.id.ad_headline_alt1)
                (nativeAdView1.headlineView as TextView).text = nativeAd.headline

                nativeAdView2.headlineView = nativeAdView2.findViewById(R.id.ad_headline_alt2)
                (nativeAdView2.headlineView as TextView).text = nativeAd.headline



                // Reklamın ikonunu ayarlayın
                nativeAdView1.iconView = nativeAdView1.findViewById(R.id.ad_app_icon_alt1)
                nativeAdView2.iconView = nativeAdView2.findViewById(R.id.ad_app_icon_alt2)

                if (nativeAd.icon != null) {
                    (nativeAdView1.iconView as ImageView).setImageDrawable(nativeAd.icon!!.drawable)
                    (nativeAdView2.iconView as ImageView).setImageDrawable(nativeAd.icon!!.drawable)



                }

                // Reklamın açıklamasını ayarlayın
                nativeAdView1.bodyView = nativeAdView1.findViewById(R.id.ad_body_alt1)
                (nativeAdView1.bodyView as TextView).text = nativeAd.body

                nativeAdView2.bodyView = nativeAdView2.findViewById(R.id.ad_body_alt2)
                (nativeAdView2.bodyView as TextView).text = nativeAd.body









                // Native reklamı yerleştirin
                nativeAdView1.setNativeAd(nativeAd)
                nativeAdView2.setNativeAd(nativeAd)



            }
            .build()
        adLoader.loadAd(AdRequest.Builder().build())



        mAdview = view.findViewById(R.id.adView)
        mAdview3 = view.findViewById(R.id.adView1)
        mAdview2 = view.findViewById(R.id.adView2)




        val adRequest = AdRequest.Builder().build()
        mAdview.loadAd(adRequest)
        mAdview2.loadAd(adRequest)
        mAdview3.loadAd(adRequest)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Release the player when the view is destroyed
        player?.release()
        player = null
    }
}