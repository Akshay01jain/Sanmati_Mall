package com.sanmati.mall.commonUtils

import java.net.NetworkInterface

actual class NetworkChecker {
    actual fun isNetworkAvailable(): Boolean {
        return try {
            // Check if any network interface is available and not loopback
            NetworkInterface.getNetworkInterfaces()
                .asSequence()
                .any { it.isUp && !it.isLoopback }
        } catch (e: Exception) {
            false // Fallback if an error occurs
        }
    }
}