package com.uib.mongo.repository.entity.document

import org.springframework.data.annotation.Id
import java.math.BigInteger

data class PartListQuestionnaire(
        var name: String,
        var parts: List<PartQuestionnaire>? = null
): GenerateUUID{
        @field:Id
        var partListId: String? = generateUid()
}
