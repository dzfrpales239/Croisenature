package com.croisenature.app.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.croisenature.app.data.prefs.UserPreferences
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    userPreferences: UserPreferences,
    onBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    
    val currentTheme by userPreferences.theme.collectAsState(initial = "nature")
    val currentSize by userPreferences.markerSize.collectAsState(initial = "moyenne")
    val currentShape by userPreferences.photoShape.collectAsState(initial = "ronde")
    val currentLines by userPreferences.nameLines.collectAsState(initial = "1")
    val currentAnimation by userPreferences.animationOnClick.collectAsState(initial = true)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Réglages", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Retour")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // SECTION THEME
            Text("🎨 Thème de l'application", style = MaterialTheme.typography.titleMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = currentTheme == "nature", onClick = { scope.launch { userPreferences.setTheme("nature") } })
                Text("Nature 🌿")
                Spacer(Modifier.width(16.dp))
                RadioButton(selected = currentTheme == "sombre", onClick = { scope.launch { userPreferences.setTheme("sombre") } })
                Text("Sombre")
                Spacer(Modifier.width(16.dp))
                RadioButton(selected = currentTheme == "clair", onClick = { scope.launch { userPreferences.setTheme("clair") } })
                Text("Clair")
            }
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            // SECTION TAILLE PHOTO
            Text("📷 Taille des photos sur la carte", style = MaterialTheme.typography.titleMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = currentSize == "petite", onClick = { scope.launch { userPreferences.setMarkerSize("petite") } })
                Text("Petite")
                Spacer(Modifier.width(16.dp))
                RadioButton(selected = currentSize == "moyenne", onClick = { scope.launch { userPreferences.setMarkerSize("moyenne") } })
                Text("Moyenne")
                Spacer(Modifier.width(16.dp))
                RadioButton(selected = currentSize == "grande", onClick = { scope.launch { userPreferences.setMarkerSize("grande") } })
                Text("Grande")
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            // SECTION FORME PHOTO
            Text("🖼️ Forme des photos", style = MaterialTheme.typography.titleMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = currentShape == "ronde", onClick = { scope.launch { userPreferences.setPhotoShape("ronde") } })
                Text("Ronde")
                Spacer(Modifier.width(16.dp))
                RadioButton(selected = currentShape == "carree", onClick = { scope.launch { userPreferences.setPhotoShape("carree") } })
                Text("Carrée")
                Spacer(Modifier.width(16.dp))
                RadioButton(selected = currentShape == "arrondie", onClick = { scope.launch { userPreferences.setPhotoShape("arrondie") } })
                Text("Arrondie")
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            // SECTION NOMBRE DE LIGNES
            Text("🏷️ Nom de l'animal (lignes)", style = MaterialTheme.typography.titleMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = currentLines == "1", onClick = { scope.launch { userPreferences.setNameLines("1") } })
                Text("1 ligne")
                Spacer(Modifier.width(16.dp))
                RadioButton(selected = currentLines == "2", onClick = { scope.launch { userPreferences.setNameLines("2") } })
                Text("2 lignes")
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            // SECTION ANIMATION
            Text("✨ Animations", style = MaterialTheme.typography.titleMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = currentAnimation,
                    onCheckedChange = { scope.launch { userPreferences.setAnimationOnClick(it) } }
                )
                Text("Animer la vignette au clic")
            }

            Spacer(Modifier.weight(1f))

            // BOUTON RESET
            Button(
                onClick = { scope.launch { userPreferences.resetToNature() } },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Réinitialiser au style Nature 🌿")
            }
        }
    }
}
