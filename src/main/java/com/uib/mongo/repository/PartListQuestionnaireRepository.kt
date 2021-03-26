package com.uib.mongo.repository

import com.uib.mongo.repository.entity.document.PartListQuestionnaire
import org.springframework.data.mongodb.repository.MongoRepository

interface PartListQuestionnaireRepository : MongoRepository<PartListQuestionnaire?, String?> {
    fun findByPartListId(partListId: String):PartListQuestionnaire?
}
