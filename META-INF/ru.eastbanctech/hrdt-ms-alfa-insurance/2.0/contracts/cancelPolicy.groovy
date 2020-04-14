package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method DELETE()
        url(value(consumer(regex("/cancel-policy\\?policyId=[0-9]+")), producer("/cancel-policy?policyId=1")))
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
                result: value(consumer("OK"), producer(anyOf("OK", "WARNING", "ERROR")))
        ]
        )
    }
}