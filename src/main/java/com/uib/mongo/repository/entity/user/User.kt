package com.uib.mongo.repository.entity.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
data class User(var name: String? = null, var password: String? = null ) {
    @field:Id
    private var id: String? = null

    @DBRef
    var roles: Set<Role>? = null
}
