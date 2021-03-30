package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

data class Question(
        var question: String? = null,
        var status: String? = null,
        var description: String? = null,
        var answers: List<QuestionAnswers>? = null
): GenerateUUID{
    @field:Id
    var questionId: String? = generateUid()
}
