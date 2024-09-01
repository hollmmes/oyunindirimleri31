package com.example.oyunindirimleri

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


class FreeFragment : Fragment() {
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


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Release the player when the view is destroyed
        player?.release()
        player = null
    }
}