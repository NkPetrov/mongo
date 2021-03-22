package com.uib.mongo.mongo.repository

import com.uib.mongo.mongo.repository.entity.user.Role
import org.springframework.data.mongodb.repository.MongoRepository

interface RoleRepository : MongoRepository<Role?, String?> {
    fun findByRole(role: String?): Role?
}
