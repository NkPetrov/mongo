package com.uib.mongo.repository

import com.uib.mongo.repository.entity.document.DomainAnswers
import org.springframework.data.mongodb.repository.MongoRepository

interface DomainAnswersRepository : MongoRepository<DomainAnswers?, String?> {
}
