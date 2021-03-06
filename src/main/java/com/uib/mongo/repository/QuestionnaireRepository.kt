package com.uib.mongo.repository

import com.uib.mongo.repository.entity.document.Questionnaire
import org.springframework.data.mongodb.repository.MongoRepository

interface QuestionnaireRepository : MongoRepository<Questionnaire?, String?> {
    fun findByCreator(creator: String?): List<Questionnaire>?
    fun findByQuestionnaireId(questionnaireId: String?): Questionnaire?
}
