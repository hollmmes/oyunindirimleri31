package com.example.oyunindirimleri

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
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class SteamFragment : Fragment() {

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
        saleTitle.text = "Son Bahar İndirimi\n 27 Kasım 2024"

        // Set up the countdown timer
        countdownTimer = view.findViewById(R.id.countdown_timer)
        startCountdown()

        // Load images into dashboard items
        val imageView1: ImageView = view.findViewById(R.id.item_image_1)
        val imageView2: ImageView = view.findViewById(R.id.item_image_2)
        val imageView3: ImageView = view.findViewById(R.id.item_image_3)
        val imageView4: ImageView = view.findViewById(R.id.item_image_4)
        val imageView5: ImageView = view.findViewById(R.id.item_image_5)



        imageView1.load("https://shared.akamai.steamstatic.com/store_item_assets/steam/spotlights/03c5ec0c9b15e4578ac5f9fd/spotlight_image_turkish.jpg?t=1724350351")
        imageView2.load("https://shared.akamai.steamstatic.com/store_item_assets/steam/spotlights/c07f36e1ff40154a846a5219/spotlight_image_turkish.jpg?t=1724369721")
        imageView3.load("https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/3050060/header_turkish.jpg?t=1724270597")
        imageView4.load("https://i.ytimg.com/vi/XPQnwJBP5jw/hqdefault.jpg")
        imageView5.load("https://shared.akamai.steamstatic.com/store_item_assets/steam/spotlights/9d334a868bb389d555c00883/spotlight_image_turkish.jpg?t=1722883193")


        val ver_imageview1: ImageView = view.findViewById(R.id.vertical_item_image_1)
        val ver_imageview2: ImageView = view.findViewById(R.id.vertical_item_image_2)
        val ver_imageview3: ImageView = view.findViewById(R.id.vertical_item_image_3)
        val vertical_item_image_reklam1: ImageView = view.findViewById(R.id.vertical_item_image_reklam1)
        val ver_imageview5: ImageView = view.findViewById(R.id.vertical_item_image_5)
        val ver_imageview6: ImageView = view.findViewById(R.id.vertical_item_image_6)
        val vertical_item_image_reklam2: ImageView = view.findViewById(R.id.vertical_item_image_reklam2)
        val ver_imageview7: ImageView = view.findViewById(R.id.vertical_item_image_7)
        val ver_imageview8: ImageView = view.findViewById(R.id.vertical_item_image_8)
        val ver_imageview9: ImageView = view.findViewById(R.id.vertical_item_image_9)
        val vertical_item_image_reklam3: ImageView = view.findViewById(R.id.vertical_item_image_reklam3)

        ver_imageview1.load("https://steamdb.info/static/img/blog/1/52a8bb8666c98fba0dcfd318e455df3698778253.png")
        ver_imageview2.load("https://steamdb.info/static/img/blog/1/ee1b420a8fa47c8795e791a05c6c0056c0e569a6.png")
        ver_imageview3.load("https://steamdb.info/static/img/blog/1/103b4210791d88a2f39532cd8983880fa1c28e51.png")
        vertical_item_image_reklam1.load("https://i.ytimg.com/vi/XPQnwJBP5jw/hqdefault.jpg")
        ver_imageview5.load("https://clan.cloudflare.steamstatic.com/images/39049601/b19e87424a7474164d3f7fcc46763cfecf1ee78b.jpg")
        ver_imageview6.load("https://steamdb.info/static/img/sales/generic.webp")
        vertical_item_image_reklam2.load("https://i.ytimg.com/vi/XPQnwJBP5jw/hqdefault.jpg")
        ver_imageview7.load("https://steamdb.info/static/img/blog/1/9b17026346a121af8eed4240892392182d90b6cc.png")
        ver_imageview8.load("https://steamdb.info/static/img/sales/autumn.webp")
        ver_imageview9.load("https://steamdb.info/static/img/sales/winter.webp")
        vertical_item_image_reklam3.load("https://i.ytimg.com/vi/XPQnwJBP5jw/hqdefault.jpg")



        val ver_imageview31: ImageView = view.findViewById(R.id.vertical_item_image_2025_1)
        ver_imageview31.load("https://steamdb.info/static/img/sales/generic.webp")

        return view
    }

    private fun startCountdown() {
        // Set the target date to 27 November 2024
        val targetDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            .parse("2024-11-27 00:00:00")?.time ?: return

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
