package com.uib.mongo.repository

import com.uib.mongo.repository.entity.document.PartQuestionnaire
import org.springframework.data.mongodb.repository.MongoRepository

interface PartQuestionnaireRepository : MongoRepository<PartQuestionnaire?, String?> {
    fun findByPartId(partId: String): PartQuestionnaire?
}
