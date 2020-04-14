package contracts.upgrade

import org.springframework.cloud.contract.spec.Contract
import ru.eastbanctech.hrdt.contract.ConsumerUtils

Contract.make {
    request {
        method POST()
        url("/dcs/upgrade-customer")
        body(
                locator: value(ConsumerUtils.anyLocator()),
                passenger: [
                        surname          : value(anyAlphaUnicode()),
                        name             : value(anyAlphaUnicode()),
                        title            : value(anyAlphaUnicode()),
                        personType       : value(ConsumerUtils.anyPersonType()),
                        uniqueCustomerId : value(anyAlphaNumeric())
                ],
                segment: [
                        date             : value(anyDate()),
                        fromIata         : value(ConsumerUtils.anyIata()),
                        toIata           : value(ConsumerUtils.anyIata()),
                        carrier          : value(ConsumerUtils.anyCarrier()),
                        operatingAirline : value(ConsumerUtils.anyCarrier()),
                        flightNumber     : value(anyPositiveInt()),
                        rbd              : value(anyNonBlankString()),
                ],
                productId: value(anyNonBlankString())
        )
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status OK()
        body(
                result: "OK",
                seatRow: value(anyPositiveInt()),
                seatNumber: value(ConsumerUtils.anySeatNumber())
        )
    }
}