package com.hafidz.praktikum1pam

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform