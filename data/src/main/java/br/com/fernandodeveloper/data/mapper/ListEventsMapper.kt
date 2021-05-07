package br.com.fernandodeveloper.data.mapper

import br.com.fernandodeveloper.domain.model.EventResponseBodyItem

fun br.com.fernandodeveloper.data.model.ListEventsResponseBody.toDomainListEventsItemsListResponseBody() =
    map {
        EventResponseBodyItem(
            date = it.date,
            description = it.description,
            id = it.id,
            image = it.image,
            latitude = it.latitude,
            longitude = it.longitude,
            people = it.people,
            price = it.price,
            title = it.title
        )
    }

fun br.com.fernandodeveloper.data.model.EventResponseBodyItem.toDomainEventResponseBodyItem() =
    br.com.fernandodeveloper.domain.model.EventResponseBodyItem(
        date = date,
        description = description,
        id = id,
        image = image,
        latitude = latitude,
        longitude = longitude,
        people = people,
        price = price,
        title = title
    )
