package com.metapoger.core.model

import android.graphics.drawable.Drawable
import java.io.Serializable
import java.util.*

data class User(
    val avatar: Drawable?,
    val name: String,
    val id: String = UUID.randomUUID().toString()
) : Serializable
