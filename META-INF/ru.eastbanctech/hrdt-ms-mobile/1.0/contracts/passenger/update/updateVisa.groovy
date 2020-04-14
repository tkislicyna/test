package contracts.passenger

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST()
        url("/dcs/update-visa")
        body(
            locator: value(consumer(anyAlphaNumeric()), producer("QQPOAO")),
            passenger:
                $(
                    birthday: value(consumer(anyDate()), producer("05.01.2000")),
                    firstName: value(consumer(anyAlphaUnicode()), producer("PETR")),
                    lastName: value(consumer(anyAlphaUnicode()), producer("PETROV")),
                    personType: value(consumer(anyOf("ADULT", "INFANT", "CHILD")),
                        producer("ADULT")),
                    title: value(consumer(anyAlphaUnicode()), producer("MR"))
                ),
            visaDto: $(
                issueDate: value(consumer(anyDate()), producer("05.01.2000")),
                expirationDate: value(consumer(anyDate()), producer("05.01.2050")),
                number: value(consumer(anyAlphaNumeric()), producer("401000123")),
                birthCountry: value(consumer(anyAlphaUnicode()), producer("RU")),
                issueCity: value(consumer(anyAlphaUnicode()), producer("NOVOSIBIRSK")),
                issueCountry: value(consumer(anyAlphaUnicode()), producer("RU")),
                entryCountry: value(consumer(anyAlphaUnicode()), producer("DE"))
            )
        )
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status OK()
        body(
            result: "OK")
    }
}