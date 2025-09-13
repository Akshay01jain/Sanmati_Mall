package com.sanmati.mall.commonUtils

class IosPlatform : Platform {
    override val name: String = "iOS (Native)"
}

// Actual implementation for iOS
actual fun getPlatform(): Platform = IosPlatform()