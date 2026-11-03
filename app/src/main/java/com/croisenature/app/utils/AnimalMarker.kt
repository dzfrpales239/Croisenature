package com.croisenature.app.utils

import android.content.Context
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import coil.Coil
import coil.request.ImageRequest
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import com.croisenature.app.R

class AnimalMarker(
    private val context: Context,
    mapView: MapView,
    private val photoUrl: String, // L'URL ou le chemin de la photo
    private val animalName: String,
    lat: Double,
    lon: Double,
    private val size: String = "moyenne",
    private val shape: String = "ronde",
    private val lines: String = "1"
) : Marker(mapView) {

    private val markerView: View = LayoutInflater.from(context)
        .inflate(R.layout.marker_animal, mapView, false).apply {
            
            val imageView = findViewById<ImageView>(R.id.markerImage)
            val textView = findViewById<TextView>(R.id.markerName)

            // Appliquer la taille choisie dans les réglages
            val sizeDp = when(size) {
                "petite" -> 48
                "grande" -> 80
                else -> 64
            }
            
            // Appliquer le nombre de lignes
            textView.maxLines = if (lines == "2") 2 else 1
            
            // Écrire le nom
            textView.text = animalName

            // Charger la photo avec Coil
            Coil.imageLoader(context).enqueue(
                ImageRequest.Builder(context)
                    .data(photoUrl)
                    .target(imageView)
                    .build()
            )
        }

    init {
        position = GeoPoint(lat, lon)
        anchor = ANCHOR_CENTER to ANCHOR_BOTTOM
    }

    override fun draw(c: Canvas, mapView: MapView, shadow: Boolean) {
        markerView.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )
        markerView.layout(0, 0, markerView.measuredWidth, markerView.measuredHeight)

        val projection = mapView.projection
        val point = projection.toPixels(position, null)

        c.save()
        c.translate(
            (point.x - markerView.measuredWidth / 2).toFloat(),
            (point.y - markerView.measuredHeight).toFloat()
        )
        markerView.draw(c)
        c.restore()
    }
}
