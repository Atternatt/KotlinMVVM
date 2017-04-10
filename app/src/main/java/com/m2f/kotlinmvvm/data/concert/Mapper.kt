package com.m2f.kotlinmvvm.data.concert

import com.m2f.kotlinmvvm.data.concert.model.ConcertDTO
import com.m2f.kotlinmvvm.domain.concert.Concert

/**
 * @author Marc Moreno
 * @version 1
 */


fun ConcertDTO.toBO(): Concert {
    return Concert(id,
            title,
            datetime,
            formattedDatetime,
            formattedLocation,
            ticketUrl,
            ticketType,
            ticketStatus,
            onSaleDatetime,
            facebookRsvpUrl,
            description)
}