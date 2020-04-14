package contracts.passenger

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST()
        url("/dcs/dcs-passengers")
        body(
                carrier: value(consumer(anyAlphaNumeric()), producer("S7")),
                departureDate: value(consumer('(\\d\\d\\d\\d)(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])'), producer("2019-05-30")),
                flightNumber: value(consumer(anyNumber()), producer("178")),
                iataFrom: value(consumer(regex('[A-Z]{3}')), producer("OVB"))
        )
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status OK()
        body([
                result   : $("OK"),
                errors   : $([]),
                warnings : $([]),
                customers: $([
                        $(
                                passenger:
                                        $(
                                                firstName:
                                                        value(consumer("VASILIY"),
                                                                producer(anyAlphaUnicode())),
                                                lastName:
                                                        value(consumer("IVANOV"),
                                                                producer(anyAlphaUnicode())),
                                                personType:
                                                        value(consumer("ADULT"),
                                                                producer(anyOf("ADULT", "INFANT", "CHILD"))),
                                                title:
                                                        value(consumer("MR"),
                                                                producer(regex('[a-zA-Z]{2}'))),
                                                birthday:
                                                        value(consumer("10.02.1990"),
                                                                producer(regex('(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.](\\d\\d\\d\\d)')))
                                        ),
                                registrationInfoList:
                                        [
                                                $(
                                                        regIdentifier:
                                                                value(consumer("2301CC4B0000C500"), producer(anyAlphaNumeric())),
                                                        flightSegment:
                                                                $(
                                                                        flightNumber:
                                                                                value(consumer("184"), producer(anyNumber())),
                                                                        iataFrom:
                                                                                value(consumer("OVB"), producer(regex('[A-Z]{3}'))),
                                                                        iataTo:
                                                                                value(consumer("DME"), producer(regex('[A-Z]{3}'))),
                                                                        carrier:
                                                                                value(consumer("S7"), producer(anyAlphaNumeric()))
                                                                ),
                                                        ticketNumber:
                                                                value(consumer("4212402050642"), producer(regex('[0-9]{13}'))),
                                                        seatInfo:
                                                                $(
                                                                        row:
                                                                                value(consumer(18), producer(anyNumber())),
                                                                        number:
                                                                                value(consumer("A"), producer(regex('[A-Z]{1}')))
                                                                ),
                                                        rbd: value(consumer("T"), producer(regex('[A-Z]{1}')))
                                                )
                                        ],
                                locator: value(consumer("KUIM9Q"), producer(anyAlphaNumeric())))

                ])
        ])
    }
}