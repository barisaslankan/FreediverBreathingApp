package com.barisaslankan.freedivingbreathing.util

import io.realm.kotlin.types.RealmInstant
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Instant.toRealmInstant(): RealmInstant {
    val sec: Long = this.epochSecond
    val nano: Int = this.nano
    return if (sec >= 0) {
        RealmInstant.from(sec, nano)
    } else {
        RealmInstant.from(sec + 1, -1_000_000 + nano)
    }
}

fun RealmInstant.toInstant(): Instant {
    val sec: Long = this.epochSeconds
    val nano: Int = this.nanosecondsOfSecond
    return if (sec >= 0) {
        Instant.ofEpochSecond(sec, nano.toLong())
    } else {
        Instant.ofEpochSecond(sec - 1, 1_000_000 + nano.toLong())
    }
}

fun RealmInstant.toFormattedString(): String {
    val instant = Instant.ofEpochSecond(this.epochSeconds, this.nanosecondsOfSecond.toLong())
    val dateTime = instant.atZone(ZoneId.systemDefault()).toLocalDate()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return dateTime.format(formatter)
}