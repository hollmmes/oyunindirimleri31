package com.example.oyunindirimleri

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
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit


class PsnFragment : Fragment() {
    private var player: ExoPlayer? = null
    private lateinit var countdownTimer: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_psn, container, false)

        // Initialize ExoPlayer
        player = ExoPlayer.Builder(requireContext()).build()
        val playerView: PlayerView = view.findViewById(R.id.player_view)
        playerView.player = player

        // Make the PlayerView non-clickable
        playerView.useController = false
        playerView.isClickable = false

        // Prepare the media item
        val mediaItem = MediaItem.fromUri(Uri.parse("https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/601220/ab101a835aa5fd4d2643b057281362faa75abeaf.mp4"))
        player?.setMediaItem(mediaItem)

        // Enable looping
        player?.repeatMode = ExoPlayer.REPEAT_MODE_ALL

        // Prepare the player
        player?.prepare()
        player?.playWhenReady = true

        // Set the sale title text programmatically
        val saleTitle = view.findViewById<TextView>(R.id.sale_title)
        saleTitle.text = "Cadılar Bayramı İndirimi\n 16 Ekim – 30 Ekim 2024"

        // Set up the countdown timer
        countdownTimer = view.findViewById(R.id.countdown_timer)
        startCountdown()

        // Load images into dashboard items
        val imageView1: ImageView = view.findViewById(R.id.item_image_1)
        val imageView2: ImageView = view.findViewById(R.id.item_image_2)
        val imageView3: ImageView = view.findViewById(R.id.item_image_3)
        val imageView4: ImageView = view.findViewById(R.id.item_image_4)
        val imageView5: ImageView = view.findViewById(R.id.item_image_5)


        imageView1.load("https://image.api.playstation.com/pr/bam-art/159/706/a6e6e7cf-d7ce-4386-99ee-65ef5970b3bb.jpg?w=1920&thumb=false")
        imageView2.load("https://i.ytimg.com/vi/XPQnwJBP5jw/hqdefault.jpg")
        imageView3.load("https://image.api.playstation.com/pr/bam-art/159/706/a6e6e7cf-d7ce-4386-99ee-65ef5970b3bb.jpg?w=1920&thumb=false")
        imageView4.load("https://i.ytimg.com/vi/XPQnwJBP5jw/hqdefault.jpg")
        imageView5.load("https://image.api.playstation.com/pr/bam-art/159/706/a6e6e7cf-d7ce-4386-99ee-65ef5970b3bb.jpg?w=1920&thumb=false")


        val ver_imageview1: ImageView = view.findViewById(R.id.vertical_item_image_1)
        val ver_imageview2: ImageView = view.findViewById(R.id.vertical_item_image_2)
        val ver_imageview3: ImageView = view.findViewById(R.id.vertical_item_image_3)
        val vertical_item_image_reklam1: ImageView = view.findViewById(R.id.vertical_item_image_reklam1)
        val ver_imageview5: ImageView = view.findViewById(R.id.vertical_item_image_5)

        ver_imageview1.load("https://preview.redd.it/lps9hfxfmgt71.jpg?auto=webp&s=a999849b75239862bdee08294f25d73532acb5a2")
        ver_imageview2.load("https://blog.playstation.com/tachyon/2022/11/901cdcdc56a232b99ab3ae8195334389640b0ee7-scaled.jpg")
        ver_imageview3.load("https://i.ytimg.com/vi/zhBo2Nct6Og/maxresdefault.jpg")
        vertical_item_image_reklam1.load("https://i.ytimg.com/vi/XPQnwJBP5jw/hqdefault.jpg")
        ver_imageview5.load("https://blog.playstation.com/tachyon/2023/12/c722f6b1a06cc9f321b81776833d09d990ad1a49.jpeg")


        return view
    }

    private fun startCountdown() {
        // Set the target date to 27 November 2024
        val targetDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            .parse("2024-10-16 00:00:00")?.time ?: return

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