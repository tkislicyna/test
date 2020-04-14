package contracts

import org.springframework.cloud.contract.spec.Contract
import ru.eastbanctech.hrdt.contract.ProducerUtils

Contract.make {
    request {
        method GET()
        url(value(consumer(regex("/policy\\?policyId=[0-9]+")), producer("/policy?policyId=1")))
        headers {
            contentType(applicationJsonUtf8())
        }
    }
    response {
        status OK()
        headers {
            contentType(applicationJsonUtf8())
        }
        body([
                policyId: value(consumer("27495443"), producer(anyNonBlankString())),
                product : $(
                        code: value(consumer("HEALTH_INSURANCE"),
                                producer(anyOf("HEALTH_INSURANCE", "EXTENDED_HEALTH_INSURANCE",
                                        "BAGGAGE_INSURANCE", "AVIA", "AVIA_ONLINE", "TRIP_CANCEL", "MEDICAL_COSTS_INSURANCE"
                                ))),
                ),
                person  : $(
                        birthDate: value(consumer("2000-06-18"), producer(anyDate())),
                        firstName: value(consumer("Igor"), producer(anyAlphaUnicode())),
                        lastName: value(consumer("Letov"), producer(anyAlphaUnicode())),
                        middleName: value(consumer("Georgievich"), producer(anyAlphaUnicode())),
                        infant: value(consumer(false), producer(anyBoolean())),
                        phone: value(consumer("9130000000"), producer(anyNonBlankString())),
                        email: value(consumer("test@gmail.com"), producer(anyEmail())),
                        document: value(consumer("1234987654"), producer(anyAlphaUnicode()))
                ),
                segments: [$(
                        carrier: value(ProducerUtils.anyCarrier()),
                        departureDateTime: value(consumer("2019-10-31T10:20"), producer(anyDateTime())),
                        arrivalDateTime: value(consumer("2019-10-31T14:05"), producer(anyDateTime())),
                        flightNumber: value(consumer("891"), producer(anyNumber())),
                        toIata: value(ProducerUtils.anyIata()),
                        fromIata: value(ProducerUtils.anyIata())
                )],
                amount  : $(
                        amount: value(consumer(250), producer(anyNumber())),
                        currency: value(ProducerUtils.anyCurrency())
                ),
                risks   : [$(
                        type: value(consumer("NS"), producer(anyOf("MR", "NSP", "NS",
                                "FLIGHT_DELAYS_PERSONAL", "SPORT", "LOSS_LUGGAGE_PERSONAL",
                                "DELAYED_LUGGAGE_PERSONAL", "GO", "LUGGAGE_MASSIVE", "FLIGHT_DELAYS_MASSIVE",
                                "NR", "PROPERTY", "EVENT", "LOSS_RESTORE_DOCUMENTS", "CL", "LUGGAGE_DAMAGE"
                        ))),
                        coverage: $(
                                amount: value(consumer(600000), producer(anyNumber())),
                                currency: value(ProducerUtils.anyCurrency())
                        )
                )],
                status  : value(consumer("ISSUING"), producer(anyOf("ISSUING",
                        "CONFIRMED",
                        "CANCELLED",
                        "DELETED"))),
                resources: [value(consumer("href"), producer(anyNonBlankString()))],
                beginDate: value(consumer("2018-12-12T00:00"), producer(anyDateTime())),
                endDate: value(consumer("2018-12-12T00:00"), producer(anyDateTime()))
        ]
        )
    }
}