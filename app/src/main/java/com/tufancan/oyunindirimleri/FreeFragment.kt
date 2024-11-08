package com.tufancan.oyunindirimleri

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.load
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.nativead.NativeAdView


class FreeFragment : Fragment() {
    lateinit var mAdview2 : AdView
    lateinit var mAdview3 : AdView

    private lateinit var database: DatabaseReference

    private var player: ExoPlayer? = null
    private lateinit var countdownTimer: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_free, container, false)

        val buttonOyunal1: Button = view.findViewById(R.id.oyunal1)


        buttonOyunal1.setOnClickListener {
            // Create an Intent to open the desired URL in a browser
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://store.epicgames.com/tr/free-games") // Replace with your desired link
            startActivity(intent)
        }


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

        database = FirebaseDatabase.getInstance().getReference("cekilis")


        // ImageView bileşenlerini bulun
        val vertical_item_image_cekilis1: ImageView = view.findViewById(R.id.vertical_item_image_cekilis1)

        // Firebase'den URL'leri çekin ve ImageView'lere yükleyin
        database.child("cekilis1").get().addOnSuccessListener { dataSnapshot ->
            val imageUrl1 = dataSnapshot.getValue(String::class.java)
            if (!imageUrl1.isNullOrEmpty()) {
                vertical_item_image_cekilis1.load(imageUrl1)
            }
        }


        // NativeAdView nesnesini bulun
        val nativeAdView1 = view.findViewById<NativeAdView>(R.id.native_ad_view_alt1)
        // AdLoader ile reklamı yükleyin
        val adLoader = AdLoader.Builder(requireContext(), "ca-app-pub-4574441267168225/5135327162")
            .forNativeAd { nativeAd ->
                // Reklamın başlığını ayarlayın
                nativeAdView1.headlineView = nativeAdView1.findViewById(R.id.ad_headline_alt1)
                (nativeAdView1.headlineView as TextView).text = nativeAd.headline
                // Reklamın ikonunu ayarlayın
                nativeAdView1.iconView = nativeAdView1.findViewById(R.id.ad_app_icon_alt1)

                if (nativeAd.icon != null) {
                    (nativeAdView1.iconView as ImageView).setImageDrawable(nativeAd.icon!!.drawable)

                }
                // Reklamın açıklamasını ayarlayın
                nativeAdView1.bodyView = nativeAdView1.findViewById(R.id.ad_body_alt1)
                (nativeAdView1.bodyView as TextView).text = nativeAd.body

                // Native reklamı yerleştirin
                nativeAdView1.setNativeAd(nativeAd)

            }
            .build()
        adLoader.loadAd(AdRequest.Builder().build())

        mAdview3 = view.findViewById(R.id.adView1)
        val adRequest = AdRequest.Builder().build()
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