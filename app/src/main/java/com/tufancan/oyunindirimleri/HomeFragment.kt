package com.tufancan.oyunindirimleri

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.nativead.NativeAdView


class HomeFragment : Fragment() {

    lateinit var mAdview : AdView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Find the steam CardView
        val steamCardView: CardView = view.findViewById(R.id.steam)
        val epicCardView: CardView = view.findViewById(R.id.epic)
        val psnCardView: CardView = view.findViewById(R.id.psn)
        val xboxCardView: CardView = view.findViewById(R.id.xbox)
        val null1CardView: CardView = view.findViewById(R.id.null1)
        // Set a click listener on the steam CardView
        steamCardView.setOnClickListener {
            // Use the Navigation component to navigate to SteamFragment
            findNavController().navigate(R.id.action_homeFragment_to_steamFragment)
        }
        epicCardView.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_epicFragment)

        }
        psnCardView.setOnClickListener {
            // Use the Navigation component to navigate to SteamFragment
            findNavController().navigate(R.id.action_homeFragment_to_psnFragment)
        }
        xboxCardView.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_xboxFragment)

        }
        null1CardView.setOnClickListener {
            // Use the Navigation component to navigate to SteamFragment
            findNavController().navigate(R.id.action_homeFragment_to_nullFragment)
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


                // Native reklamı yerleştirin
                nativeAdView1.setNativeAd(nativeAd)
            }
            .build()
        adLoader.loadAd(AdRequest.Builder().build())



        return view
    }



}