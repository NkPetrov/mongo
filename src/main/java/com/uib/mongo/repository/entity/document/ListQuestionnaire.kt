package com.uib.mongo.repository.entity.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigInteger
import java.util.*

data class ListQuestionnaire(
        var listName: String,
        var parts: List<PartListQuestionnaire>? = null
): GenerateUUID{
        @field:Id
        var listId: String? = generateUid()

}
