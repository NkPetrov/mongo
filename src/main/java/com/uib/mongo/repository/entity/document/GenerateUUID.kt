package com.uib.mongo.repository.entity.document

import java.util.*

abstract interface GenerateUUID {
   fun generateUid() = UUID.randomUUID().toString()
}
