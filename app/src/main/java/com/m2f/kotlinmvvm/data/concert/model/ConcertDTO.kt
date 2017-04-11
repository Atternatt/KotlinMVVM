package com.m2f.kotlinmvvm.data.concert.model

import com.google.gson.annotations.SerializedName

/**
 * @author Marc Moreno
 * *
 * @version 1
 */

class ConcertDTO(@SerializedName("id") val id: String,
                 @SerializedName("artist_id") val artistId: String,
                 @SerializedName("url") val url: String,
                 @SerializedName("datetime") val datetime: String,
                 @SerializedName("venue") val venue: VenueDTO) {

    inner class VenueDTO(@SerializedName("name") val name: String,
                         @SerializedName("latitude") val latitude: String,
                         @SerializedName("longitude") val longitude: String,
                         @SerializedName("city") val city: String,
                         @SerializedName("region") val region: String,
                         @SerializedName("country") val country: String)
}