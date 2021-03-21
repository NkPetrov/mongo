package com.uib.mongo.mongo.repository

import com.uib.mongo.mongo.repository.entity.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User?, String?> {
    fun findByEmail(email: String?): User?
}
