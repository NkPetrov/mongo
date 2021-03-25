package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id

data class PartQuestionnaire(
        var name: String,
        var domainSections: List<DomainQuestionnaire>? = null
): GenerateUUID {
        @field:Id
        var partId: String? = generateUid()
}