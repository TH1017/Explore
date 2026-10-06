package com.example.explore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import org.maplibre.android.MapLibre
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style

class MainActivity : ComponentActivity() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // MapLibreを初期化
        MapLibre.getInstance(this)

        // MapViewを作成
        mapView = MapView(this)

        // 画面いっぱいにMapViewを表示
        setContentView(mapView)

        // MapViewの初期化
        mapView.onCreate(savedInstanceState)

        // 地図の準備ができたら処理
        mapView.getMapAsync { map ->

            Log.d(
                "ExploreMap",
                "★★★ MAP ASYNC OK ★★★"
            )

            map.setStyle(
                "https://tiles.openfreemap.org/styles/bright",
                object : Style.OnStyleLoaded {

                    override fun onStyleLoaded(style: Style) {

                        Log.d(
                            "ExploreMap",
                            "★★★ STYLE LOADED ★★★"
                        )

                        // 大阪付近を表示
                        map.cameraPosition =
                            CameraPosition.Builder()
                                .target(
                                    LatLng(
                                        34.6937,
                                        135.5023
                                    )
                                )
                                .zoom(12.0)
                                .build()
                    }
                }
            )
        }
    }

    override fun onStart() {
        super.onStart()

        if (::mapView.isInitialized) {
            mapView.onStart()
        }
    }

    override fun onResume() {
        super.onResume()

        if (::mapView.isInitialized) {
            mapView.onResume()
        }
    }

    override fun onPause() {

        if (::mapView.isInitialized) {
            mapView.onPause()
        }

        super.onPause()
    }

    override fun onStop() {

        if (::mapView.isInitialized) {
            mapView.onStop()
        }

        super.onStop()
    }

    override fun onDestroy() {

        if (::mapView.isInitialized) {
            mapView.onDestroy()
        }

        super.onDestroy()
    }
}