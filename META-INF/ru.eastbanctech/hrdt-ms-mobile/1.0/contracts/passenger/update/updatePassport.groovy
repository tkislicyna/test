package contracts.passenger

import org.springframework.cloud.contract.spec.Contract
import ru.eastbanctech.hrdt.contract.ConsumerUtils

Contract.make {
    request {
        method POST()
        url("/dcs/update-passport")
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
            passengerDocument: $(
                expirationDate: value(consumer(anyDate()), producer("05.01.2000")),
                number: value(consumer(anyAlphaNumeric()), producer("5555555555")),
                nationality: value(consumer(anyAlphaUnicode()), producer("RU")),
                personDocType: value(consumer(anyOf("Passport", "ForeignPassport", "BirthCertificate",
                    "OtherDocument", "OtherCountryPassport")), producer("Passport"))
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