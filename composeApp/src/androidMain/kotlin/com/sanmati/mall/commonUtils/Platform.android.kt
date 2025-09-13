package com.sanmati.mall.commonUtils

class AndroidPlatform : Platform {
    override val name = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()