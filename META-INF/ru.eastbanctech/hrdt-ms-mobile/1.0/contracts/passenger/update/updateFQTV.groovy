package contracts.passenger

import org.springframework.cloud.contract.spec.Contract
import ru.eastbanctech.hrdt.contract.ConsumerUtils

Contract.make {
    request {
        method POST()
        url("/dcs/update-fqtv")
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
            frequentFlyerCard: $(
                carrier: value(ConsumerUtils.anyCarrier()),
                number: value(consumer(anyAlphaNumeric()), producer("401000123")),
                status: value(consumer(anyOf("Gold", "Silver", "Platinum", "Ruby",
                    "Sapphlre", "Emerald", "")), producer("Gold"))
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