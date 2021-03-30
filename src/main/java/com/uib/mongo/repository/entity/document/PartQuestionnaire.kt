package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

data class PartQuestionnaire(
        var name: String,
        var parentId: String,
        var children : PartQuestionnaire? = null,

        @field:DBRef
        var questions: List<Question>? = null
): GenerateUUID {
        @field:Id
        var partId: String? = generateUid()
}
