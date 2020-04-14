package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method GET()
        url("/health")
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status OK()
        body("OK")
    }
}