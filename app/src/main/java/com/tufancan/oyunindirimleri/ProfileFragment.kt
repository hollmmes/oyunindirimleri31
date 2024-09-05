package com.tufancan.oyunindirimleri

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
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.nativead.NativeAdView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.app.AlertDialog
import android.widget.Toast
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class ProfileFragment : Fragment() {
    lateinit var mAdview : AdView
    private var player: ExoPlayer? = null
    private lateinit var countdownTimer: TextView
    private lateinit var database: DatabaseReference

    private var rewardedAd: RewardedAd? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize ExoPlayer
        player = ExoPlayer.Builder(requireContext()).build()
        val playerView: PlayerView = view.findViewById(R.id.player_view)
        playerView.player = player

        // Make the PlayerView non-clickable
        playerView.useController = false
        playerView.isClickable = false

        // Prepare the media item
        val mediaItem = MediaItem.fromUri(Uri.parse("https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/601220/7d44c9655d6092c31035c7cc971a6cede8b90c76.mp4"))
        player?.setMediaItem(mediaItem)

        // Enable looping
        player?.repeatMode = ExoPlayer.REPEAT_MODE_ALL

        // Prepare the player
        player?.prepare()
        player?.playWhenReady = true

        // Set the sale title text programmatically
        val saleTitle = view.findViewById<TextView>(R.id.sale_title)
        saleTitle.text = "Profile Hoş geldin\nSana özel bu alanda çekilişlere katılabilir\nBildirimleri açarak indirimleri kaçırmazsın!"

        // Set up the countdown time

        // Load images into dashboard items
        database = FirebaseDatabase.getInstance().getReference("cekiliyap")

        val vertical_item_image_cekilis1: ImageView = view.findViewById(R.id.vertical_item_image_cekilis1)
        val vertical_item_image_cekilis2: ImageView = view.findViewById(R.id.vertical_item_image_cekilis2)

        // Firebase'den URL'leri çekin ve ImageView'lere yükleyin
        database.child("cekbakalim1").get().addOnSuccessListener { dataSnapshot ->
            val imageUrl1 = dataSnapshot.getValue(String::class.java)
            if (!imageUrl1.isNullOrEmpty()) {
                vertical_item_image_cekilis1.load(imageUrl1)
            }
        }

        database.child("cekbakalim2").get().addOnSuccessListener { dataSnapshot ->
            val imageUrl2 = dataSnapshot.getValue(String::class.java)
            if (!imageUrl2.isNullOrEmpty()) {
                vertical_item_image_cekilis2.load(imageUrl2)
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

        mAdview = view.findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdview.loadAd(adRequest)




        // Set up dogrula_buton0 click event
        val dogrulaButton0: Button = view.findViewById(R.id.dogrula_button0)
        dogrulaButton0.setOnClickListener {
            loadRewardedAd()
            showRewardedAd()
        }

        // Set up dogrula_button1 click event
        val dogrulaButton1: Button = view.findViewById(R.id.dogrula_button1)
        dogrulaButton1.setOnClickListener {
            loadRewardedAd()
            showRewardedAd()
        }

        // Bildirimleri Aktifleştir butonunu bulun ve tıklama olayını ayarlayın
        val bildirimButton: Button = view.findViewById(R.id.bildirim)
        bildirimButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Bildirimleri Etkinleştirme")
            builder.setMessage("Bildirimleri etkinleştirmek istiyor musunuz?")

            builder.setPositiveButton("Evet") { dialog, _ ->
                // Bildirimleri etkinleştirme kodu buraya gelecek
                Toast.makeText(requireContext(), "Bildirimler etkinleştirildi", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            builder.setNegativeButton("Hayır") { dialog, _ ->
                dialog.dismiss() // Sadece dialogu kapat
            }

            builder.create().show()
        }

        return view
    }

    private fun loadRewardedAd() {
        val adRequest = AdRequest.Builder().build()

        if (isAdded) {
            RewardedAd.load(
                requireContext(),
                "ca-app-pub-4574441267168225/3835522081",
                adRequest,
                object : RewardedAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        rewardedAd = null
                        if (isAdded) {
                            Toast.makeText(requireContext(), "lütfen tekrar deneyiniz", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onAdLoaded(ad: RewardedAd) {
                        rewardedAd = ad
                    }
                }
            )
        }
    }

    private fun showRewardedAd() {
        if (rewardedAd != null && isAdded) {
            rewardedAd?.show(requireActivity(), OnUserEarnedRewardListener {
                // Handle the reward
                if (isAdded) {
                    Toast.makeText(requireContext(), "Çekilişe Katıldınız!", Toast.LENGTH_SHORT).show()
                    saveDataToFirebase()
                }
            })
        } else {
            if (isAdded) {
                Toast.makeText(requireContext(), "Lütfen reklamın tamamını izleyiniz.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun saveDataToFirebase() {
        // Retrieve the values from adrescekilis1 and adrescekilis2 (assuming they are TextViews)
        val adrescekilis1Text = view?.findViewById<TextView>(R.id.adrescekilis1)?.text.toString()
        val adrescekilis2Text = view?.findViewById<TextView>(R.id.adrescekilis2)?.text.toString()

        // Save the values to Firebase
        val dataMap = mapOf(
            "adrescekilis1" to adrescekilis1Text,
            "adrescekilis2" to adrescekilis2Text
        )

        database.child("cekilisKatilim").push().setValue(dataMap)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Çekilişe katılım bilgileriniz kaydedildi!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Bilgiler kaydedilirken bir hata oluştu.", Toast.LENGTH_SHORT).show()
            }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        // Release the player when the view is destroyed
        player?.release()
        player = null
    }
}
