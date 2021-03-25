package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id

data class DomainRow (
        var status: String? = null,
        var description: String? = null
): GenerateUUID {
    @field:Id
    var domainRow: String? = generateUid()
}
