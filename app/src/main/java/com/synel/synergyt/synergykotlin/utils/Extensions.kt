package com.synel.synergyt.synergykotlin.utils

import android.content.res.AssetManager
import android.content.res.Resources
import androidx.annotation.RawRes


fun AssetManager.readAssetsFile(fileName: String): String =
    open(fileName).bufferedReader().use { it.readText() }

fun Resources.readResourcesFile(@RawRes id: Int): String =
    openRawResource(id).bufferedReader().use { it.readText() }

fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

fun String.addCharAtIndex(char: Char, index: Int) =
    StringBuilder(this).apply { insert(index, char) }.toString()
