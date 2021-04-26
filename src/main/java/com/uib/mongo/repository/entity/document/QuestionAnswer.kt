package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id

data class QuestionAnswer(
    var answer: String? = null
) : GenerateUUID {
    @field:Id
    var answerId: String = generateUid()
}
