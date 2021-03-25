package com.uib.mongo.configuration

import com.github.cloudyrock.mongock.ChangeLog
import com.github.cloudyrock.mongock.ChangeSet
import com.uib.mongo.repository.ListQuestionnaireRepository
import com.uib.mongo.repository.QuestionnaireRepository
import com.uib.mongo.repository.UserRepository
import com.uib.mongo.repository.entity.document.DomainAnswers
import com.uib.mongo.repository.entity.document.DomainQuestionnaire
import com.uib.mongo.repository.entity.document.ListQuestionnaire
import com.uib.mongo.repository.entity.document.PartListQuestionnaire
import com.uib.mongo.repository.entity.document.PartQuestionnaire
import com.uib.mongo.repository.entity.document.Question
import com.uib.mongo.repository.entity.document.Questionnaire
import com.uib.mongo.repository.entity.user.User

@ChangeLog
class DatabaseChangelog {

    @ChangeSet(order = "001", id = "1", author = "Nikolay")
    fun setDatabase(userRepository: UserRepository) =
            userRepository.insert(User("nik","nik"))

    @ChangeSet(order = "002", id = "2", author = "Nikolay")
    fun setQuestionnaire(questionnaireRepository: QuestionnaireRepository, listQuestionnaireRepository: ListQuestionnaireRepository) {
        var domainAnswers: List<DomainAnswers> = listOf(DomainAnswers(status = "Not",description = "not"), DomainAnswers(status = "Not",description = "not"))
        var domain: List<DomainQuestionnaire> = listOf(DomainQuestionnaire(nameDomainSection = "Inner Users"), DomainQuestionnaire(nameDomainSection = "External Users"))
        var questions: List<Question> = listOf(Question("Question 1"),Question("Question 2"),Question("Question 3"))

        var part1: List<PartQuestionnaire> = listOf(PartQuestionnaire(name = "Inner 1",questions,domain),PartQuestionnaire(name = "Inner 2",questions,domain))

        var part2: List<PartListQuestionnaire> = listOf(PartListQuestionnaire(name = "External 1", part1), PartListQuestionnaire(name = "External 2", part1))

        var list1: List<ListQuestionnaire> = listOf(ListQuestionnaire(listName = "List1",part2), ListQuestionnaire(listName = "List2",part2), ListQuestionnaire(listName = "List3",part2))

        var b = listQuestionnaireRepository.insert(list1)
        questionnaireRepository.insert(Questionnaire(name = "Поведенческая Биометрия", lists = b))
    }
}
