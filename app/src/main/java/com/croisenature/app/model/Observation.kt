package com.croisenature.app.model

data class Observation(
    val id: String,             // Un identifiant unique pour chaque animal
    val speciesName: String,    // Le nom (ex: Mante religieuse)
    val photoUrl: String,       // Le lien ou le chemin de la photo
    val latitude: Double,       // La position GPS (Nord/Sud)
    val longitude: Double,      // La position GPS (Est/Ouest)
    val country: String,        // Le pays (ex: France, Palestine)
    val city: String,           // La ville
    val date: Long = System.currentTimeMillis() // La date de l'observation
)
