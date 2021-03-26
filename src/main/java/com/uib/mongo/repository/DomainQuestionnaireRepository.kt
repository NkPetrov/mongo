package com.uib.mongo.repository

import com.uib.mongo.repository.entity.document.DomainQuestionnaire
import org.springframework.data.mongodb.repository.MongoRepository

interface DomainQuestionnaireRepository : MongoRepository<DomainQuestionnaire?, String?> {
}
