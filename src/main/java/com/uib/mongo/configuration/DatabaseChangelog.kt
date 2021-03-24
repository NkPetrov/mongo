package com.uib.mongo.configuration

import com.github.cloudyrock.mongock.ChangeLog
import com.github.cloudyrock.mongock.ChangeSet
import com.uib.mongo.repository.ListQuestionnaireRepository
import com.uib.mongo.repository.QuestionnaireRepository
import com.uib.mongo.repository.UserRepository
import com.uib.mongo.repository.entity.document.ListQuestionnaire
import com.uib.mongo.repository.entity.document.Questionnaire
import com.uib.mongo.repository.entity.user.User

@ChangeLog
class DatabaseChangelog {

    @ChangeSet(order = "001", id = "1", author = "Nikolay")
    fun setDatabase(userRepository: UserRepository) =
            userRepository.insert(User("nik","nik"))

    @ChangeSet(order = "002", id = "2", author = "Nikolay")
    fun setQuestionnaire(questionnaireRepository: QuestionnaireRepository, listQuestionnaireRepository: ListQuestionnaireRepository) {
        var list1: List<ListQuestionnaire> = listOf(ListQuestionnaire(listName = "List1"), ListQuestionnaire(listName = "List2"), ListQuestionnaire(listName = "List3"))
        var b = listQuestionnaireRepository.insert(list1)
        questionnaireRepository.insert(Questionnaire(name = "Поведенческая Биометрия", lists = b))
    }
}
