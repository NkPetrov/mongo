package com.uib.mongo.repository

import com.uib.mongo.repository.entity.document.QuestionAnswers
import org.springframework.data.mongodb.repository.MongoRepository

interface QuestionAnswersRepository : MongoRepository<QuestionAnswers?, String?> {
    fun findByQuestionAnswerId(questionAnswerId: String): QuestionAnswers?
}
