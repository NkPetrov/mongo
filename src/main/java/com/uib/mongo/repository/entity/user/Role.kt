package com.uib.mongo.repository.entity.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.IndexDirection
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "role")
data class Role(var role: String) {
    @field:Id
    var id: String? = null
}
