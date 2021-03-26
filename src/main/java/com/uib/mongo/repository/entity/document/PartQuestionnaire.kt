package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document("partQuestionnaire")
data class PartQuestionnaire(
        var name: String,
        @field:DBRef
        var questions: List<Question>? = null,
        @field:DBRef
        var domainSections: List<DomainQuestionnaire>? = null
): GenerateUUID {
        @field:Id
        var partId: String? = generateUid()
}
