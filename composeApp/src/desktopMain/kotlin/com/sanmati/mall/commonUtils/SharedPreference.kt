package com.sanmati.mall.commonUtils

import java.util.prefs.Preferences


actual object SharedPreference {
    private val prefs: Preferences = Preferences.userRoot().node("MyAppPrefs")

    actual fun putString(key: String, value: String) {
        prefs.put(key, value)
    }

    actual fun getString(key: String, defaultValue: String?): String? {
        return prefs.get(key, defaultValue)
    }

    actual fun remove(key: String) {
        prefs.remove(key)
    }

    actual fun clear() {
        prefs.keys().forEach { prefs.remove(it) }
    }
}
