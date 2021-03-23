package com.uib.mongo.repository.entity.document

import org.springframework.data.annotation.Id

data class PartQuestionnaire(
        @field:Id
        var partId: Long? = null,
        var name: String? = null,
        var domainSections: List<DomainQuestionnaire>? = null
)
