package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id

class QuestionAnswers(var answer: String): GenerateUUID {
    @field:Id
    var questionAnswerId: String = generateUid()
}
