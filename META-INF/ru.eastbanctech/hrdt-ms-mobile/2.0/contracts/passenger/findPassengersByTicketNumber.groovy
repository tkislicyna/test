package contracts.passenger

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST()
        url("/dcs/find-by-ticket-number")
        body(
            ticketNumber: value(consumer(regex('[0-9]{13}')), producer('4212402050642'))
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
                            id:
                                value(consumer("2301CC4B0000C500"), producer(anyAlphaNumeric())),
                            birthday:
                                value(consumer("10.02.1990"),
                                    producer(regex('(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.](\\d\\d\\d\\d)'))),
                            documents:
                                [$(
                                    personDocType:
                                        value(consumer("PASSPORT"),
                                            producer(anyOf("PASSPORT", "VISA",
                                                "OTHER_DOCUMENT",
                                                "BIRTH_CERTIFICATE"))),
                                    documentNumber:
                                        value(consumer("3434433453"),
                                            producer(regex('[0-9]+'))),
                                    firstName:
                                        value(consumer("VASILIY"),
                                            producer(anyAlphaUnicode())),
                                    lastName:
                                        value(consumer("IVANOV"),
                                            (producer(anyAlphaUnicode())))
                                )],
                            contactInformation:
                                $(email:
                                    value(consumer("test@test.te"), producer(anyEmail())),
                                    phone: value(consumer("+75454545454"), producer(regex('\\+[0-9]+'))),
                                    preferredLanguage:
                                        value(consumer("RU"), producer(anyAlphaUnicode()))

                                                        )
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