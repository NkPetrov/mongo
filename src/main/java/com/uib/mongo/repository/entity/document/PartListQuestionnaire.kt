package com.uib.mongo.repository.entity.document

import org.springframework.data.annotation.Id
import java.math.BigInteger

data class PartListQuestionnaire(
        var name: String,
        var parts: List<PartQuestionnaire>? = null
){
        @field:Id
        var partListId: BigInteger? = null
}
