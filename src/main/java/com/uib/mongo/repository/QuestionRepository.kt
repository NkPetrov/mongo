package com.uib.mongo.repository

import com.uib.mongo.repository.entity.document.Question
import org.springframework.data.mongodb.repository.MongoRepository

interface QuestionRepository : MongoRepository<Question?, String?> {
    fun findByQuestionId(questionId: String): Question?
}
