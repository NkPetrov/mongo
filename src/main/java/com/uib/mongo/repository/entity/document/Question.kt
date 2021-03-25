package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id

data class Question(var question: String): GenerateUUID{
    @field:Id
    var questionId: String = generateUid()
}
