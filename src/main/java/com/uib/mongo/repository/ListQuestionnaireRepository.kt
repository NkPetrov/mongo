package com.uib.mongo.repository

import com.uib.mongo.repository.entity.document.ListQuestionnaire
import org.springframework.data.mongodb.repository.MongoRepository

interface ListQuestionnaireRepository : MongoRepository<ListQuestionnaire?, String?> {
    fun findByListId(lisId: String): ListQuestionnaire?
}
