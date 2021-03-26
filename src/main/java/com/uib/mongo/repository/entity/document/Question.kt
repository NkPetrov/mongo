package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("questions")
data class Question(
        var question: String? = null
): GenerateUUID{
    @field:Id
    var questionId: String? = generateUid()
}
