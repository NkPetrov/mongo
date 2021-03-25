package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id

data class DomainQuestionnaire(
        var nameDomainSection: String,
        var domainAnswers: List<DomainAnswers>? = null
): GenerateUUID {
        @field:Id
        var domainId: String? = generateUid()
}
