package com.uib.mongo.repository

import com.uib.mongo.repository.entity.document.Question
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface QuestionRepository : MongoRepository<Question?, String?> {
    fun findByQuestionId(questionId: String): Question?
}
