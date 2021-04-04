package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import lombok.Value
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef

@Value
data class Question(
        @field:DBRef
        var answers: MutableList<QuestionAnswers>? = null,
        var status: String? = null,
        var description: String? = null,
        override var number: String? = null,
        override var name: String? = null
) : GenerateUUID, SectionQuestionnaire {
    @field:Id
    var questionId: String = generateUid()
}
