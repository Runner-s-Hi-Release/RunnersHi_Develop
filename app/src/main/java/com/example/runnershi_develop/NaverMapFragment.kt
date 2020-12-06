package com.example.runnershi_develop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.runnershi_develop.data.Coordinate
import com.example.runnershi_develop.databinding.FragmentNaverMapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.overlay.PathOverlay

class NaverMapFragment : Fragment(), OnMapReadyCallback {
    private var naverMap: NaverMap? = null
    lateinit var path: PathOverlay
    private var mapCoords = mutableListOf<LatLng>()
    lateinit var mapFragment: MapFragment


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentNaverMapBinding>(
            inflater, R.layout.fragment_naver_map, container, false
        )

        mapFragment = childFragmentManager.findFragmentById(R.id.mapv_rec_detail) as MapFragment?
            ?: MapFragment.newInstance().also {
                childFragmentManager.beginTransaction().add(R.id.mapv_rec_detail, it).commit()
            }

        return binding.root
    }

    fun updateCoordinates(coordinates: ArrayList<Coordinate>?) {
        if (!coordinates.isNullOrEmpty()) {
            mapCoords = emptyList<LatLng>().toMutableList()
            for (coordinate in coordinates) {
                mapCoords.add(LatLng(coordinate.latitude, coordinate.longitude))
            }
            mapFragment.getMapAsync(this)
        }
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap

        if (mapCoords.size >= 2) {
            path = PathOverlay()
            path.coords = mapCoords

            naverMap?.let {
                path.map = it

                val marker = Marker()
                marker.position = path.coords[path.coords.size - 1]
                marker.icon = OverlayImage.fromResource(R.drawable.icon_location)
                marker.map = naverMap

                path.width = 20
                path.outlineWidth = 0
                path.color = ContextCompat.getColor(requireContext(), R.color.blue_map)

                val coord = mapCoords[mapCoords.size - 1]
                it.moveCamera(CameraUpdate.scrollTo(coord))
            }
        }
    }
}