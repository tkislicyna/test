package contracts.seat

import org.springframework.cloud.contract.spec.Contract
import ru.eastbanctech.hrdt.contract.ConsumerUtils

Contract.make {
    request {
        method POST()
        url("/dcs/change-seat/v2")
        body(
                segment: [
                        date            : value(anyDate()),
                        fromIata        : value(ConsumerUtils.anyIata()),
                        toIata          : value(ConsumerUtils.anyIata()),
                        carrier         : value(ConsumerUtils.anyCarrier()),
                        operatingAirline: value(ConsumerUtils.anyCarrier()),
                        flightNumber    : value(anyPositiveInt()),
                        rbd             : value(ConsumerUtils.anyRbd())
                ],
                requestedSeatNumber: value(ConsumerUtils.anySeatNumber()),
                requestedSeatRow: value(anyPositiveInt()),
                locator: value(ConsumerUtils.anyLocator()),
                passenger: [
                        firstName : value(anyNonBlankString()),
                        lastName  : value(anyNonBlankString()),
                        personType: value(ConsumerUtils.anyPersonType()),
                        title     : value(ConsumerUtils.anyTitle())
                ]
        )
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status OK()
        body(
                result: "OK",
                seatRow: 1,
                seatNumber: "A"
        )
    }
}