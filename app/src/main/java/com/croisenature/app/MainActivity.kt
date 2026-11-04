package com.croisenature.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.croisenature.app.data.prefs.UserPreferences
import com.croisenature.app.model.Observation
import com.croisenature.app.ui.map.MapScreen
import com.croisenature.app.ui.settings.SettingsScreen
import com.croisenature.app.ui.theme.CroiseNatureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialisation des préférences
        val userPreferences = UserPreferences(applicationContext)

        // Quelques animaux d'exemple pour tester la carte
        val sampleObservations = listOf(
            Observation(
                id = "1",
                speciesName = "Pie bavarde",
                photoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Pica_pica_-_Compans_Caffarelli_-_01.jpg/320px-Pica_pica_-_Compans_Caffarelli_-_01.jpg",
                latitude = 48.8566,
                longitude = 2.3522,
                country = "France",
                city = "Paris"
            ),
            Observation(
                id = "2",
                speciesName = "Mante religieuse",
                photoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Mantis_religiosa_MHNT.jpg/320px-Mantis_religiosa_MHNT.jpg",
                latitude = 36.7538,
                longitude = 3.0588,
                country = "Algérie",
                city = "Alger"
            )
        )

        setContent {
            CroiseNatureTheme {
                var showSettings by remember { mutableStateOf(false) }
                
                // On lit les préférences pour la carte
                val markerSize by userPreferences.markerSize.collectAsState(initial = "moyenne")
                val photoShape by userPreferences.photoShape.collectAsState(initial = "ronde")
                val nameLines by userPreferences.nameLines.collectAsState(initial = "1")

                Scaffold(
                    topBar = {
                        if (!showSettings) {
                            TopAppBar(
                                title = { Text("🌿 CroiseNature") },
                                actions = {
                                    TextButton(onClick = { showSettings = true }) {
                                        Text("Réglages ⚙️")
                                    }
                                }
                            )
                        }
                    }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding).fillMaxSize()) {
                        if (showSettings) {
                            SettingsScreen(
                                userPreferences = userPreferences,
                                onBack = { showSettings = false }
                            )
                        } else {
                            MapScreen(
                                observations = sampleObservations,
                                markerSize = markerSize,
                                markerShape = photoShape,
                                nameLines = nameLines
                            )
                        }
                    }
                }
            }
        }
    }
}
