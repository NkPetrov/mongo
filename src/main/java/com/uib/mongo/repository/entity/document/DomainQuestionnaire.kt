package com.uib.mongo.repository.entity.document

import org.springframework.data.annotation.Id
import java.math.BigInteger

data class DomainQuestionnaire(
        var nameDomainSection: String,
        var domainQuestion: List<DomainQuestion>? = null
){
        @field:Id
        var domainId: BigInteger? = null
}
