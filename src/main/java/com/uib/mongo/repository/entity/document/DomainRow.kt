package com.uib.mongo.repository.entity.document

import org.springframework.data.annotation.Id
import java.math.BigInteger

data class DomainRow (
        var status: String? = null,
        var description: String? = null
){
    @field:Id
    var domainRow: BigInteger? = null
}
