package com.uib.mongo.mongo.repository.entity.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "questionnaire")
class Questionnaire(
        @field:Id
        var questionnaireId: Long? = null,
        var name: String? = null,
        var lists: List<ListQuestionnaire>? = null
)
