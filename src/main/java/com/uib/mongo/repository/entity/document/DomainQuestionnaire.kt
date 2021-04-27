package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef

data class DomainQuestionnaire(
        var nameDomainSection: String?,
): GenerateUUID {
        @field:Id
        var domainId: String = generateUid()
}
