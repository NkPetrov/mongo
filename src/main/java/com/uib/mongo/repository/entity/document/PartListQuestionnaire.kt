package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef

data class PartListQuestionnaire(
        var name: String,
        @DBRef
        var parts: List<PartQuestionnaire>? = null
): GenerateUUID {
        @field:Id
        var partListId: String? = generateUid()
}
