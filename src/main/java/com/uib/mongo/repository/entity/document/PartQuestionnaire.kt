package com.uib.mongo.repository.entity.document

import org.springframework.data.annotation.Id
import java.math.BigInteger

data class PartQuestionnaire(
        var name: String,
        var domainSections: List<DomainQuestionnaire>? = null
){
        @field:Id
        var partId: BigInteger? = null
}
