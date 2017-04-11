package com.m2f.kotlinmvvm.data.concert

import com.m2f.kotlinmvvm.data.concert.model.ConcertDTO
import com.m2f.kotlinmvvm.domain.concert.Concert
import com.m2f.kotlinmvvm.domain.concert.Venue

/**
 * @author Marc Moreno
 * @version 1
 */


fun ConcertDTO.VenueDTO.toBO(): Venue {
    return Venue(
            name,
            latitude,
            longitude,
            city,
            region,
            country)
}

fun ConcertDTO.toBO(): Concert {
    return Concert(id,
            artistId,
            url,
            datetime,
            venue.toBO())
}