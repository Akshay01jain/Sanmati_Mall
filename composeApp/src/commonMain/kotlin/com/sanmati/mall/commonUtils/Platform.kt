package com.sanmati.mall.commonUtils

interface Platform {
    val name : String
}

expect fun getPlatform() : Platform