package com.m2f.kotlinmvvm.data.concert.model

import com.google.gson.annotations.SerializedName

class ConcertDTO(@SerializedName("id") val id: Int,
                 @SerializedName("title") val title: String?,
                 @SerializedName("datetime") val datetime: String?,
                 @SerializedName("formatted_datetime") val formattedDatetime: String?,
                 @SerializedName("formatted_location") val formattedLocation: String?,
                 @SerializedName("ticket_url") val ticketUrl: String?,
                 @SerializedName("ticket_type") val ticketType: String?,
                 @SerializedName("ticket_status") val ticketStatus: String?,
                 @SerializedName("on_sale_datetime") val onSaleDatetime: String?,
                 @SerializedName("facebook_rsvp_url") val facebookRsvpUrl: String?,
                 @SerializedName("description") val description: String?)