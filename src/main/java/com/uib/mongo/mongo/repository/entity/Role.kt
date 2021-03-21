package com.uib.mongo.mongo.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.IndexDirection
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "role")
class Role {
    @Id
    var id: String? = null

    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    var role: String? = null
}
