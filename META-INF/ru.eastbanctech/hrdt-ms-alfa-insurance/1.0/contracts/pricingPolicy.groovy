package contracts

import org.springframework.cloud.contract.spec.Contract
import ru.eastbanctech.hrdt.contract.ConsumerUtils
import ru.eastbanctech.hrdt.contract.ProducerUtils

Contract.make {
    request {
        method POST()
        url("/pricing-policy")
        body(
                productType: value(consumer(anyOf("HEALTH_INSURANCE", "EXTENDED_HEALTH_INSURANCE",
                        "BAGGAGE_INSURANCE", "AVIA", "AVIA_ONLINE", "TRIP_CANCEL", "MEDICAL_COSTS_INSURANCE")),
                        producer("AVIA")),
                insureds: [
                        $(
                                birthDate: value(consumer(anyDate()), producer("2000-06-10")),
                                firstName: value(consumer(anyAlphaUnicode()), producer("Igor")),
                                lastName: value(consumer(anyAlphaUnicode()), producer("Letov")),
                                middleName: value(consumer(anyAlphaUnicode()), producer("Georgievich")),
                        )],
                segments: [$(
                        carrier: value(ConsumerUtils.anyCarrier()),
                        departureDateTime: value(consumer(ConsumerUtils.anyDateTimeWithOptionalMilliseconds()), producer("2019-10-31T10:20")),
                        arrivalDateTime: value(consumer(ConsumerUtils.anyDateTimeWithOptionalMilliseconds()), producer("2019-10-31T14:05")),
                        flightNumber: value(consumer(anyNumber()), producer("891")),
                        toIata: value(ConsumerUtils.anyIata()),
                        fromIata: value(ConsumerUtils.anyIata())
                )]
        )
        headers {
            contentType(applicationJsonUtf8())
        }
    }

    response {
        status OK()
        headers {
            contentType(applicationJsonUtf8())
        }
        body([policies: [
                $(product: $(
                        "code": value(consumer("HEALTH_INSURANCE"),
                                producer(anyOf("HEALTH_INSURANCE", "EXTENDED_HEALTH_INSURANCE",
                                        "BAGGAGE_INSURANCE", "AVIA", "AVIA_ONLINE", "TRIP_CANCEL", "MEDICAL_COSTS_INSURANCE"
                                ))),
                ),
                        person: $(
                                birthDate: value(consumer("2000-06-10"), producer(anyDate())),
                                firstName: value(consumer("Igor"), producer(anyAlphaUnicode())),
                                lastName: value(consumer("Letov"), producer(anyAlphaUnicode())),
                                middleName: value(consumer("Georgievich"), producer(anyAlphaUnicode())),
                                infant: value(consumer(false), producer(anyBoolean()))
                        ),
                        segments: [$(
                                carrier: value(ProducerUtils.anyCarrier()),
                                departureDateTime: value(consumer("2019-10-31T10:20"), producer(anyDateTime())),
                                arrivalDateTime: value(consumer("2019-10-31T14:05"), producer(anyDateTime())),
                                flightNumber: value(consumer("891"), producer(anyNumber())),
                                toIata: value(ProducerUtils.anyIata()),
                                fromIata: value(ProducerUtils.anyIata())
                        )],
                        amount: $(
                                amount: value(consumer(250), producer(anyNumber())),
                                currency: value(consumer("RUB"), producer(ProducerUtils.anyCurrency()))
                        ),
                        risks: [$(
                                type: value(consumer("NS"), producer(anyOf("MR", "NSP", "NS",
                                        "FLIGHT_DELAYS_PERSONAL", "SPORT", "LOSS_LUGGAGE_PERSONAL",
                                        "DELAYED_LUGGAGE_PERSONAL", "GO", "LUGGAGE_MASSIVE", "FLIGHT_DELAYS_MASSIVE",
                                        "NR", "PROPERTY", "EVENT", "LOSS_RESTORE_DOCUMENTS", "CL", "LUGGAGE_DAMAGE"
                                ))),
                                coverage: $(
                                        amount: value(consumer(600000), producer(anyNumber())),
                                        currency: value(consumer("RUB"), producer(ProducerUtils.anyCurrency()))
                                )
                        )],
                        status: value(consumer("ISSUING"), producer(anyOf("ISSUING",
                                "CONFIRMED", "CANCELLED", "DELETED"))),
                        beginDate: value(consumer("2018-12-12T00:00"), producer(anyDateTime()))
                )
        ]
        ])
    }
}