package com.uib.mongo.repository

import com.uib.mongo.repository.entity.document.ListQuestionnaire
import com.uib.mongo.repository.entity.document.Questionnaire
import org.springframework.data.mongodb.repository.MongoRepository
import java.math.BigInteger

interface ListQuestionnaireRepository : MongoRepository<ListQuestionnaire?, String?> {
    fun findByListId(lisId: BigInteger): ListQuestionnaire?
}
