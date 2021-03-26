package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef

data class ListQuestionnaire(
        var listName: String,
        @DBRef
        var parts: List<PartListQuestionnaire>? = null
): GenerateUUID {
        @field:Id
        var listId: String? = generateUid()
}
