package com.uib.mongo.repository.entity.document

import org.springframework.data.annotation.Id
import java.math.BigInteger

data class DomainRow (
        var status: String? = null,
        var description: String? = null
): GenerateUUID{
    @field:Id
    var domainRow: String? = generateUid()
}
