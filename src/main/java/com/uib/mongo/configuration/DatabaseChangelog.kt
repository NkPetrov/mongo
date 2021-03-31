package com.uib.mongo.configuration

import com.github.cloudyrock.mongock.ChangeLog
import com.github.cloudyrock.mongock.ChangeSet
import com.uib.mongo.repository.DomainQuestionnaireRepository
import com.uib.mongo.repository.ListQuestionnaireRepository
import com.uib.mongo.repository.PartQuestionnaireRepository
import com.uib.mongo.repository.QuestionRepository
import com.uib.mongo.repository.QuestionnaireRepository
import com.uib.mongo.repository.UserRepository
import com.uib.mongo.repository.entity.document.DomainQuestionnaire
import com.uib.mongo.repository.entity.document.ListQuestionnaire
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
                         partQuestionnaireRepo: PartQuestionnaireRepository,
                         questionRepo: QuestionRepository,
                         domainQuestionnaireRepo: DomainQuestionnaireRepository,
                         ) {

        //add domain model
        var domain: List<DomainQuestionnaire> = listOf(
                domainQuestionnaireRepo.insert(DomainQuestionnaire(nameDomainSection = "Inner Users")),
                domainQuestionnaireRepo.insert(DomainQuestionnaire(nameDomainSection = "External Users")))

        //add questions
        var question1: List<Question> = listOf(
                questionRepo.insert(Question(name = "Question 1")),
                questionRepo.insert(Question(name = "Question 2")),
                questionRepo.insert(Question(name = "Question 3")))

        //add questions
        var question2: List<Question> = listOf(
                questionRepo.insert(Question(name = "Question 4")),
                questionRepo.insert(Question(name = "Question 5")),
                questionRepo.insert(Question(name = "Question 6")))

        //add questions
        var question3: List<Question> = listOf(
                questionRepo.insert(Question(name = "Question 7")),
                questionRepo.insert(Question(name = "Question 8")),
                questionRepo.insert(Question(name = "Question 9")))

        //add inner part
        var part0: List<PartQuestionnaire> = listOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner 1.1.1",parentId = "inner", children = null ,questions = question1, number = "1.1.1.1")))

        //add inner part
        var part1: List<PartQuestionnaire> = listOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner 1.1",parentId = "inner", children = part0, questions = question2, number = "1.1.1")))

        var part2: List<PartQuestionnaire> = listOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner 1",parentId = "inner", children = part1, questions = question3, number = "1.1")))

        //add inner part
        var part_0: List<PartQuestionnaire> = listOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner 2.2.2",parentId = "inner", children = null ,questions = question1, number = "2.2.2.2")))

        //add inner part
        var part_1: List<PartQuestionnaire> = listOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner 2.2",parentId = "inner", children = part_0, questions = question2, number = "2.2.2")))

        //add inner part
        var part_2: List<PartQuestionnaire> = listOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner 2",parentId = "inner", children = part_1, questions = question3, number = "2.2")))

        //add external part
        var partList: List<PartQuestionnaire> = listOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner1", children = part2, parentId = "list", number = "1")),
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner2", children = part_2, parentId = "list", number = "2")))


        //add lists
        var list1: List<ListQuestionnaire> = listOf(
                listQuestionnaireRepo.insert(ListQuestionnaire(listName = "List1", parts = partList, domainSections = domain)),
                listQuestionnaireRepo.insert(ListQuestionnaire(listName = "List2", parts = partList, domainSections = domain)))

        questionnaireRepository.insert(Questionnaire(name = "Поведенческая Биометрия", lists = list1, creator = "admin"))
    }
}
