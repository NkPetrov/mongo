package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document("lists")
data class ListQuestionnaire(
        var listName: String,

        @field:DBRef
        var parts: MutableList<PartQuestionnaire>? = mutableListOf(),

        @field:DBRef
        var domainSections: MutableList<DomainQuestionnaire>? = mutableListOf()

): GenerateUUID {
    @field:Id
    var listId: String = generateUid()
}
