package com.uib.mongo.configuration

import com.github.cloudyrock.mongock.ChangeLog
import com.github.cloudyrock.mongock.ChangeSet
import com.uib.mongo.repository.DomainQuestionnaireRepository
import com.uib.mongo.repository.ListQuestionnaireRepository
import com.uib.mongo.repository.PartQuestionnaireRepository
import com.uib.mongo.repository.QuestionAnswersRepository
import com.uib.mongo.repository.QuestionRepository
import com.uib.mongo.repository.QuestionnaireRepository
import com.uib.mongo.repository.UserRepository
import com.uib.mongo.repository.entity.document.DomainQuestionnaire
import com.uib.mongo.repository.entity.document.ListQuestionnaire
import com.uib.mongo.repository.entity.document.PartQuestionnaire
import com.uib.mongo.repository.entity.document.Question
import com.uib.mongo.repository.entity.document.QuestionAnswers
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
                         questionAnswersRepo: QuestionAnswersRepository,
                         domainQuestionnaireRepo: DomainQuestionnaireRepository,
                         ) {

        //add domain model
        var domain: MutableList<DomainQuestionnaire> = mutableListOf(
                domainQuestionnaireRepo.insert(DomainQuestionnaire(nameDomainSection = "Inner Users")),
                domainQuestionnaireRepo.insert(DomainQuestionnaire(nameDomainSection = "External Users")))

        //add answers of questions

        var answers: MutableList<QuestionAnswers> = mutableListOf(
                questionAnswersRepo.save(QuestionAnswers("Answer 1")),
                questionAnswersRepo.save(QuestionAnswers("Answer 2")),
                questionAnswersRepo.save(QuestionAnswers("Answer 3")))

        //add questions
        var question1: MutableList<Question> = mutableListOf(
                questionRepo.insert(Question(name = "Question 1", answers = answers)),
                questionRepo.insert(Question(name = "Question 2", answers = answers)),
                questionRepo.insert(Question(name = "Question 3", answers = answers)))

        //add questions
        var question2: MutableList<Question> = mutableListOf(
                questionRepo.insert(Question(name = "Question 4", answers = answers)),
                questionRepo.insert(Question(name = "Question 5", answers = answers)),
                questionRepo.insert(Question(name = "Question 6", answers = answers)))

        //add questions
        var question3: MutableList<Question> = mutableListOf(
                questionRepo.insert(Question(name = "Question 7", answers = answers)),
                questionRepo.insert(Question(name = "Question 8", answers = answers)),
                questionRepo.insert(Question(name = "Question 9", answers = answers)))

        //add inner part
        var part0: MutableList<PartQuestionnaire> = mutableListOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner 1.1.1",parentId = "inner", children = null ,questions = question1, number = "")))

        //add inner part
        var part1: MutableList<PartQuestionnaire> = mutableListOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner 1.1",parentId = "inner", children = part0, questions = question2, number = "")))

        var part2: MutableList<PartQuestionnaire> = mutableListOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner 1",parentId = "inner", children = part1, questions = question3, number = "")))

        //add inner part
        var part_0: MutableList<PartQuestionnaire> = mutableListOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner 2.2.2",parentId = "inner", children = null ,questions = question1, number = "")))

        //add inner part
        var part_1: MutableList<PartQuestionnaire> = mutableListOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner 2.2",parentId = "inner", children = part_0, questions = question2, number = "")))

        //add inner part
        var part_2: MutableList<PartQuestionnaire> = mutableListOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner 2",parentId = "inner", children = part_1, questions = question3, number = "")))

        //add external part
        var partList: MutableList<PartQuestionnaire> = mutableListOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner1", children = part2, parentId = "list", number = "")),
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Inner2", children = part_2, parentId = "list", number = "")))


        //add lists
        var list1: MutableList<ListQuestionnaire> = mutableListOf(
                listQuestionnaireRepo.insert(ListQuestionnaire(listName = "List1", parts = partList, domainSections = domain)),
                listQuestionnaireRepo.insert(ListQuestionnaire(listName = "List2", parts = partList, domainSections = domain)))

        questionnaireRepository.insert(Questionnaire(name = "Поведенческая Биометрия", lists = list1, creator = "admin"))
    }
}
