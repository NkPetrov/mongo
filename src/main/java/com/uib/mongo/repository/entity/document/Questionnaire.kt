package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "questionnaire")
data class Questionnaire(
        var name: String,
        var creator: String? = null,
        @DBRef
        var lists: List<ListQuestionnaire>? = null
): GenerateUUID {
        @field:Id
        var questionnaireId: String = generateUid()
}
