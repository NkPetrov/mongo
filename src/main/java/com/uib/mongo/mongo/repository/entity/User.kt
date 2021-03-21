package com.uib.mongo.mongo.repository.entity

import com.uib.mongo.mongo.repository.entity.Role
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.IndexDirection
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
class User {
    @Id
    var id: String? = null

    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    var email: String? = null
    var password: String? = null
    var fullname: String? = null
    var isEnabled = false

    @DBRef
    var roles: Set<Role>? = null
}
