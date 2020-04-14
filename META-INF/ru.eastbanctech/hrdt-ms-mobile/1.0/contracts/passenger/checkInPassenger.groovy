package contracts.passenger

import org.springframework.cloud.contract.spec.Contract
import ru.eastbanctech.hrdt.contract.ConsumerUtils

Contract.make {
    request {
        method POST()
        url("/dcs/check-in")
        body(
            locator: value(consumer(anyAlphaNumeric()), producer("QQPOAO")),
            passengers: [
                $(
                    birthday: value(consumer(anyDate()), producer("05.01.2000")),
                    firstName: value(consumer(anyAlphaUnicode()), producer("PETR")),
                    lastName: value(consumer(anyAlphaUnicode()), producer("PETROV")),
                    personType: value(consumer(anyOf("ADULT", "INFANT", "CHILD")),
                        producer("ADULT")),
                    title: value(consumer(anyAlphaUnicode()), producer("MR"))
                )],
            segments: [$(
                carrier: value(ConsumerUtils.anyCarrier()),
                departureDateTime: value(consumer(anyDateTime()), producer("2019-08-21T17:39:44.937")),
                flightNumber: value(consumer(anyNumber()), producer("178")),
                iataFrom: value(ConsumerUtils.anyIata()),
                iataTo: value(ConsumerUtils.anyIata())
            )]
        )
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status OK()
        body([
            result              : $("OK"),
            errors              : $([]),
            warnings            : $([]),
            registrationDataList: $([
                $(
                    passenger:
                        $(
                            firstName:
                                value(consumer("PETR"),
                                    producer(anyAlphaUnicode())),
                            lastName:
                                value(consumer("PETROV"),
                                    producer(anyAlphaUnicode())),
                            personType:
                                value(consumer("ADULT"),
                                    producer(anyOf("ADULT", "INFANT", "CHILD"))),
                            title:
                                value(consumer("MR"),
                                    producer(regex('[a-zA-Z]{2}')))
                        ),
                    idPassenger:
                        value(consumer("2301CC4B0000C500"), producer(anyAlphaNumeric())),
                    passengerDataList:
                        [
                            $(

                                flightData:
                                    $(
                                        regIdentifier:
                                            value(consumer("2301CC4B0000C500"), producer(anyAlphaNumeric())),
                                        flightNumber:
                                            value(consumer("178"), producer(anyNumber())),
                                        from:
                                            value(consumer("OVB"), producer(regex('[A-Z]{3}'))),
                                        to:
                                            value(consumer("DME"), producer(regex('[A-Z]{3}'))),
                                        departureDate: value(consumer("2019-07-20"), producer(anyDate()))
                                    ),
                                seatInfo:
                                    $(
                                        row:
                                            value(consumer(18), producer(anyNumber())),
                                        number:
                                            value(consumer("A"), producer(regex('[A-Z]{1}')))
                                    ),

                                registrationStatus:
                                    $(
                                        status: "OK",
                                        errorsMessage: []
                                    )
                            )
                        ]
                )
            ])
        ])
    }
}