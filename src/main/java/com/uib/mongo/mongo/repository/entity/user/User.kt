package com.uib.mongo.mongo.repository.entity.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.IndexDirection
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
data class User(var name: String?, var password: String? ) {
    @field:Id
    private var id: String? = null

    @DBRef
    private var roles: List<Role>? = null
}
