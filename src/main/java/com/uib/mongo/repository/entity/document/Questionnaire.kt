package com.uib.mongo.repository.entity.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigInteger
import java.util.*

@Document(collection = "questionnaire")
data class Questionnaire(
        var name: String,
        var lists: List<ListQuestionnaire>? = null
){
        @field:Id
        var questionnaireId: String? = UUID.randomUUID().toString()
}
