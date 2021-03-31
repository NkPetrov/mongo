package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document("lists")
data class ListQuestionnaire(
        var listName: String,

        @field:DBRef
        var parts: List<PartQuestionnaire>? = null,

        @field:DBRef
        var domainSections: List<DomainQuestionnaire>? = null

): GenerateUUID {
    @field:Id
    var listId: String = generateUid()
}
