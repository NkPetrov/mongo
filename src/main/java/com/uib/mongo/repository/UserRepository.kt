package com.uib.mongo.repository

import com.uib.mongo.repository.entity.user.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User?, String?> {
    fun findByName(name: String?): User?
}
