package com.cruckman900.mymarvelcomiccollection.helpers

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import java.security.MessageDigest
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class Helper: Application() {
    companion object {
        lateinit var attributionText: String

        @RequiresApi(Build.VERSION_CODES.O)
        fun getDateRangeString() : String {
            val current = LocalDateTime.now()
            val prev = current.minusWeeks(1)

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

            val today = current.format(formatter)
            val lastWeek = prev.format(formatter)

            return "$lastWeek,$today"
        }

        fun setAttribText(attribText: String) {
            attributionText = attribText
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getTimeStamp() : String {
            return DateTimeFormatter
                    .ofPattern("yyyy-MM-ddHH:mm:ss.SSSSSS")
                    .withZone(ZoneOffset.UTC)
                    .format(Instant.now())
        }

        fun buildHash(ts: String, privateKey: String, apiKey: String, type: String) : String {
            val prehash = ts + privateKey + apiKey
            val bytes = MessageDigest
                    .getInstance(type)
                    .digest(prehash.toByteArray())
            return bytes.toHex()
        }

        fun ByteArray.toHex() : String {
            return joinToString("")  { "%02x".format(it) }
        }
    }
}