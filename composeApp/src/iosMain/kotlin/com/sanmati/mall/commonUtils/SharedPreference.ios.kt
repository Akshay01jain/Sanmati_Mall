package com.sanmati.mall.commonUtils

import platform.Foundation.NSUserDefaults

actual object SharedPreference {
    private val prefs: NSUserDefaults = NSUserDefaults.standardUserDefaults()

    actual fun putString(key: String, value: String) {
        prefs.setObject(value, forKey = key)
    }

    actual fun getString(key: String, defaultValue: String?): String? {
        return prefs.stringForKey(key) ?: defaultValue
    }

    actual fun remove(key: String) {
        prefs.removeObjectForKey(key)
    }

    actual fun clear() {
        prefs.dictionaryRepresentation().keys.forEach {
            prefs.removeObjectForKey(it.toString())
        }
    }
}
