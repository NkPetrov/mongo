package com.uib.mongo.repository

import com.uib.mongo.repository.entity.document.QuestionAnswer
import org.springframework.data.mongodb.repository.MongoRepository

interface QuestionAnswersRepository : MongoRepository<QuestionAnswer?, String?> {
    fun findByAnswerId(questionAnswerId: String): QuestionAnswer?
}
