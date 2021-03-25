package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id

data class DomainQuestionnaire(
        var nameDomainSection: String,
        var domainQuestion: List<DomainQuestion>? = null
): GenerateUUID {
        @field:Id
        var domainId: String? = generateUid()
}
