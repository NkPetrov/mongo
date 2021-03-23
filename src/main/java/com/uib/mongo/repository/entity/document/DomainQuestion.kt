package com.uib.mongo.repository.entity.document

import org.springframework.data.annotation.Id

data class DomainQuestion(
        @field:Id
        var questionId: Long,
        var questions: String? = null,
        var domainRows: List<DomainRow>? = null
)
