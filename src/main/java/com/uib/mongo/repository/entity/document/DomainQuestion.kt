package com.uib.mongo.repository.entity.document

import org.springframework.data.annotation.Id

data class DomainQuestion(
        var questions: String,
        var domainRows: List<DomainRow>? = null
): GenerateUUID{
        @field:Id
        var questionId: String? = generateUid()
}
