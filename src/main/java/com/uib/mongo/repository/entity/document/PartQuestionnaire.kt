package com.uib.mongo.repository.entity.document

import com.uib.mongo.repository.entity.document.UUID.GenerateUUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef

data class PartQuestionnaire(

        var parentId: String? = null,

        @field:DBRef
        var children : MutableList<PartQuestionnaire>? = null,

        @field:DBRef
        var questions: MutableList<Question>? = null,

        override var number: String? = null,
        override var name: String? = null
): GenerateUUID, SectionQuestionnaire {
        @field:Id
        var partId: String = generateUid()
}
