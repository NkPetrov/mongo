package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID

class QuestionAnswers(var answer: String): GenerateUUID {
    var questionAnswerId: String = generateUid()
}
