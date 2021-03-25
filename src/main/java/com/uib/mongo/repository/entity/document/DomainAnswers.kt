package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id

data class DomainAnswers(
        var status: String? = null,
        var description: String? = null
): GenerateUUID {
        @field:Id
        var questionId: String? = generateUid()
}
