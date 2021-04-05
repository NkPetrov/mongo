package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("questionAnswers")
data class QuestionAnswers(
        var answer: String
): GenerateUUID {
    @field:Id
    var questionAnswerId: String = generateUid()
}
