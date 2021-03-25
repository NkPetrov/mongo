package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id

data class PartListQuestionnaire(
        var name: String,
        var parts: List<PartQuestionnaire>? = null
): GenerateUUID {
        @field:Id
        var partListId: String? = generateUid()
}
