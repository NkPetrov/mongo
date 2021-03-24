package com.uib.mongo.repository.entity.document

import org.springframework.data.annotation.Id
import java.math.BigInteger

data class DomainQuestion(
        var questions: String,
        var domainRows: List<DomainRow>? = null
){
        @field:Id
        var questionId: BigInteger? = null
}
