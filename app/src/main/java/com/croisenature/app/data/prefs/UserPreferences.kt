package com.croisenature.app.data.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "croisenature_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        val THEME_KEY = stringPreferencesKey("theme")
        val SIZE_KEY = stringPreferencesKey("marker_size")
        val SHAPE_KEY = stringPreferencesKey("photo_shape")
        val LINES_KEY = stringPreferencesKey("name_lines")
        val ANIMATION_KEY = booleanPreferencesKey("animation_on_click")
    }

    private val defaultTheme = "nature"
    private val defaultSize = "moyenne"
    private val defaultShape = "ronde"
    private val defaultLines = "1"
    private val defaultAnimation = true

    val theme: Flow<String> = context.dataStore.data
        .map { it[THEME_KEY] ?: defaultTheme }

    val markerSize: Flow<String> = context.dataStore.data
        .map { it[SIZE_KEY] ?: defaultSize }

    val photoShape: Flow<String> = context.dataStore.data
        .map { it[SHAPE_KEY] ?: defaultShape }

    val nameLines: Flow<String> = context.dataStore.data
        .map { it[LINES_KEY] ?: defaultLines }

    val animationOnClick: Flow<Boolean> = context.dataStore.data
        .map { it[ANIMATION_KEY] ?: defaultAnimation }

    suspend fun setTheme(value: String) {
        context.dataStore.edit { it[THEME_KEY] = value }
    }

    suspend fun setMarkerSize(value: String) {
        context.dataStore.edit { it[SIZE_KEY] = value }
    }

    suspend fun setPhotoShape(value: String) {
        context.dataStore.edit { it[SHAPE_KEY] = value }
    }

    suspend fun setNameLines(value: String) {
        context.dataStore.edit { it[LINES_KEY] = value }
    }

    suspend fun setAnimationOnClick(value: Boolean) {
        context.dataStore.edit { it[ANIMATION_KEY] = value }
    }

    suspend fun resetToNature() {
        context.dataStore.edit {
            it[THEME_KEY] = defaultTheme
            it[SIZE_KEY] = defaultSize
            it[SHAPE_KEY] = defaultShape
            it[LINES_KEY] = defaultLines
            it[ANIMATION_KEY] = defaultAnimation
        }
    }
}
