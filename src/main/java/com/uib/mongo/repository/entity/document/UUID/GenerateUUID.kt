package com.uib.mongo.repository.entity.document.UUID

import java.util.*

interface GenerateUUID {
   fun generateUid() = UUID.randomUUID().toString()
}
