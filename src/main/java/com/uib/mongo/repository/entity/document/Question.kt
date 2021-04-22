package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef

data class Question(
    var status: String? = null,
    var description: String? = null,
    override var number: String? = null,
    override var name: String? = null,
    @field:DBRef
    var answers: MutableList<QuestionAnswer>? = mutableListOf(),
) : GenerateUUID, SectionQuestionnaire {
    @field:Id
    var questionId: String = generateUid()
}
