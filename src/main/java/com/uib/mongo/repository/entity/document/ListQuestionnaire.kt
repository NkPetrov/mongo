package com.uib.mongo.repository.entity.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "lists")
data class ListQuestionnaire(
        @field:Id
        var listId: Long,
        var listName: String,
        var part: List<PartQuestionnaire>? = null
)
