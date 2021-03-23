package com.uib.mongo.repository.entity.document

import org.springframework.data.annotation.Id

data class DomainQuestionnaire(
        @field:Id
        var domainId: Long? = null,
        var nameDomainSection: String,
        var domainQuestion: List<DomainQuestion>? = null
)
