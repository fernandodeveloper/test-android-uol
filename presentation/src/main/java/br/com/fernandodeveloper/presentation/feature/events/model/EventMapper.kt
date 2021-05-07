package br.com.fernandodeveloper.presentation.feature.events.model

import br.com.fernandodeveloper.domain.model.EventResponseBodyItem
import br.com.fernandodeveloper.presentation.extensions.formatarMoeda
import br.com.fernandodeveloper.presentation.extensions.toDateBR

fun EventResponseBodyItem.toPresentationEventItem() =
    EventItem(
        date = date.toDateBR(),
        description = description,
        id = id,
        image = image,
        latitude = "$LATITUDE$latitude",
        longitude = "$LONGITUDE$longitude",
        price = "$DESC_PRICE ${price.formatarMoeda()}",
        title = title,
        contentToBeShare = createContentToBeShare()
    )

fun List<EventResponseBodyItem>.toPresentationEventItemList() =
    map {
        EventItem(
            date = it.date.toDateBR(),
            description = it.description,
            id = it.id,
            image = it.image,
            latitude = it.latitude.toString(),
            longitude = it.longitude.toString(),
            price = "$DESC_PRICE ${it.price.formatarMoeda()}",
            title = it.title,
            contentToBeShare = it.createContentToBeShare()
        )
    }

fun EventResponseBodyItem.createContentToBeShare() =
    title + "\n" + description + "\n" + date.toDateBR() + "\n" + "$DESC_PRICE$price"

private const val DESC_PRICE = "Valor "
private const val LATITUDE = "Lat: "
private const val LONGITUDE = "Long: "
