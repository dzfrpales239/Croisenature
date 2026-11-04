package com.croisenature.app.ui.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.croisenature.app.model.Observation
import com.croisenature.app.utils.AnimalMarker
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

@Composable
fun MapScreen(
    observations: List<Observation>,
    markerSize: String = "moyenne",
    markerShape: String = "ronde",
    nameLines: String = "1"
) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            MapView(context).apply {
                // Configuration de base de la carte
                setTileSource(TileSourceFactory.MAPNIK) // Carte OpenStreetMap gratuite
                setMultiTouchControls(true)
                
                // Centrer la carte sur la France par défaut
                controller.setZoom(5.5)
                controller.setCenter(GeoPoint(46.603354, 1.888334))
            }
        },
        update = { mapView ->
            // On efface les anciennes vignettes
            mapView.overlays.clear()

            // On ajoute une vignette pour chaque animal observé
            observations.forEach { obs ->
                val marker = AnimalMarker(
                    context = mapView.context,
                    mapView = mapView,
                    photoUrl = obs.photoUrl,
                    animalName = obs.speciesName,
                    lat = obs.latitude,
                    lon = obs.longitude,
                    size = markerSize,
                    shape = markerShape,
                    lines = nameLines
                )
                mapView.overlays.add(marker)
            }

            // On rafraîchit la carte pour afficher les nouvelles vignettes
            mapView.invalidate()
        }
    )
}
