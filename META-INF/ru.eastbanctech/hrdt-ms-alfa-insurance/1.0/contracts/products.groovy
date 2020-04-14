package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method GET()
        url("/products")
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
                items: [
                        [
                                code       : value(consumer("HEALTH_INSURANCE"), producer(anyOf("HEALTH_INSURANCE", "EXTENDED_HEALTH_INSURANCE", "BAGGAGE_INSURANCE", "AVIA", "AVIA_ONLINE", "TRIP_CANCEL", "MEDICAL_COSTS_INSURANCE"))),
                                type       : value(consumer("RAILWAY"), producer(anyOf("RAILWAY", "AIR", "TRAVEL_AIR"))),
                                description: value(anyNonEmptyString())
                        ]
                ]
        ])
    }
}