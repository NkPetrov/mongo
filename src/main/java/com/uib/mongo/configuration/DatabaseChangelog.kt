package com.uib.mongo.configuration

import com.github.cloudyrock.mongock.ChangeLog
import com.github.cloudyrock.mongock.ChangeSet
import com.uib.mongo.repository.DomainAnswersRepository
import com.uib.mongo.repository.DomainQuestionnaireRepository
import com.uib.mongo.repository.ListQuestionnaireRepository
import com.uib.mongo.repository.PartListQuestionnaireRepository
import com.uib.mongo.repository.PartQuestionnaireRepository
import com.uib.mongo.repository.QuestionRepository
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
    fun setQuestionnaire(questionnaireRepository: QuestionnaireRepository,
                         listQuestionnaireRepo: ListQuestionnaireRepository,
                         partListQuestionnaireRepo: PartListQuestionnaireRepository,
                         partQuestionnaireRepo: PartQuestionnaireRepository,
                         questionRepo: QuestionRepository,
                         domainQuestionnaireRepo: DomainQuestionnaireRepository,
                         domainAnswersRepo: DomainAnswersRepository
                         ) {

        //add ansvers domain
        var domainAnswers: List<DomainAnswers> = listOf(
                domainAnswersRepo.insert(DomainAnswers(status = "Not",description = "not")),
                domainAnswersRepo.insert(DomainAnswers(status = "Not",description = "not")))

        //add domain model
        var domain: List<DomainQuestionnaire> = listOf(
                domainQuestionnaireRepo.insert(DomainQuestionnaire(nameDomainSection = "Inner Users")),
                domainQuestionnaireRepo.insert(DomainQuestionnaire(nameDomainSection = "External Users")))

        //add questions
        var questions: List<Question> = listOf(
                questionRepo.insert(Question("Question 1")),
                questionRepo.insert(Question("Question 2")),
                questionRepo.insert(Question("Question 3")))

        //add inner part
        var part1: List<PartQuestionnaire> = listOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner 1",questions,domain)),
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner 2",questions,domain)))

        //add external part
        var part2: List<PartListQuestionnaire> = listOf(
                partListQuestionnaireRepo.insert(PartListQuestionnaire(name = "External 1", part1)),
                partListQuestionnaireRepo.insert(PartListQuestionnaire(name = "External 2", part1)))

        //add lists
        var list1: List<ListQuestionnaire> = listOf(
                listQuestionnaireRepo.insert(ListQuestionnaire(listName = "List1",part2)),
                listQuestionnaireRepo.insert(ListQuestionnaire(listName = "List2",part2)),
                listQuestionnaireRepo.insert(ListQuestionnaire(listName = "List3",part2)))

        questionnaireRepository.insert(Questionnaire(name = "Поведенческая Биометрия", lists = list1, creator = "admin"))
    }
}
