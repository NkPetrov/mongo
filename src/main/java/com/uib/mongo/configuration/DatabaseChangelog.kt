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
import com.uib.mongo.repository.entity.document.QuestionAnswer
import com.uib.mongo.repository.entity.document.Questionnaire
import com.uib.mongo.repository.entity.user.User

@ChangeLog
class DatabaseChangelog {

    @ChangeSet(order = "001", id = "1", author = "Nikolay")
    fun setDatabase(userRepository: UserRepository) =
            userRepository.insert(User("nik","nik"))

    @ChangeSet(order = "003", id = "3", author = "Nikolay")
    fun setQuestionnaireDemo(questionnaireRepository: QuestionnaireRepository,
                         listQuestionnaireRepo: ListQuestionnaireRepository,
                         partQuestionnaireRepo: PartQuestionnaireRepository,
                         questionRepo: QuestionRepository,
                         questionAnswersRepo: QuestionAnswersRepository,
                         domainQuestionnaireRepo: DomainQuestionnaireRepository,
    ) {

        //add domain model
        var domain: MutableList<DomainQuestionnaire> = mutableListOf(
                domainQuestionnaireRepo.insert(DomainQuestionnaire(nameDomainSection = "Внутренние пользователи")),
                domainQuestionnaireRepo.insert(DomainQuestionnaire(nameDomainSection = "Внешние пользователи")))

        //add answers of questions

        var answersAuth: MutableList<QuestionAnswer> = mutableListOf(
                questionAnswersRepo.save(QuestionAnswer(answer = "Да")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Нет")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Внутренние пользователи отсутствуют")))

        var answersFactorsAuth: MutableList<QuestionAnswer> = mutableListOf(
                questionAnswersRepo.save(QuestionAnswer(answer = "Да")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Нет")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Фактор аутентификации не используется")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Внутренние пользователи отсутствуют")))

        var answersFactorsAuthQ2111: MutableList<QuestionAnswer> = mutableListOf(
                questionAnswersRepo.save(QuestionAnswer(answer = "Да (SHA256)")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Да (SHA384)")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Да (SHA512)")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Да (SHA3-224)")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Да (SHA3-256)")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Да (SHA3-384)")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Да (SHA3-512)")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Да (Другое)")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Нет")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Пароли не хранятся")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Фактор аутентификации не используется")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Внутренние пользователи отсутствуют")))

        var answersFactorsAuthQ2112: MutableList<QuestionAnswer> = mutableListOf(
                questionAnswersRepo.save(QuestionAnswer(answer = "Да, индивидуальная для каждого пользователя")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Да, общая для всех")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Не используется")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Фактор аутентификации не используется")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Внутренние пользователи отсутствуют")))

        var answersFactorsAuthQ2114: MutableList<QuestionAnswer> = mutableListOf(
                questionAnswersRepo.save(QuestionAnswer(answer = "Да")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Нет")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Не web-приложение")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Внутренние пользователи отсутствуют")),
                questionAnswersRepo.save(QuestionAnswer(answer = "Фактор аутентификации не используется")))

        //add questions
        var questionsAuth: MutableList<Question> = mutableListOf(
                questionRepo.insert(Question(name = "Реализована ли аутентификация при входе в систему?", answers = answersAuth)),
                questionRepo.insert(Question(name = "Реализована ли возможность использования нескольких факторов аутентификации?", answers = answersAuth)),
                questionRepo.insert(Question(name = "Вполняется ли проверка на блокировку пользователей?", answers = answersAuth)),
                questionRepo.insert(Question(name = "Существует ли возможность включения/отключения факторов аутентификации?", answers = answersAuth)),
                questionRepo.insert(Question(name = "До ввода аутентификационных данных реализована возможность включения и отключения автоматизированного теста Тьюринга (CAPTCHA)?", answers = answersAuth)),
                questionRepo.insert(Question(name = "Вполняется ли проверка на блокировку пользователей?", answers = answersAuth)))

        //add questions
        var questionFactorsAuth: MutableList<Question> = mutableListOf(
                questionRepo.insert(Question(name = "В системе используется логин и пароль?", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Обеспечивается маскирование пароля при его вводе в поле ввода?", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Реализована обязательная смена пароля при первом входе?", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Реализована возможность ограничения жизни пароля?", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Реализована обязательная смена пароля при истечении жизни пароля?", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "В случае реализации обязательной смены пароля доступ к системе не предоставляется до момента, пока пароль не будет изменен?", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Предусмотрена возможность настройки длины пароля? \n" +
                        "(в комментариях просьба указать минимально возможную длину)", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Предусмотрена возможность настройки контроля количества неправильных последовательных попыток ввода пароля?", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Происходит ли блокировка учетной записи после истечения количества неправильных попыток ввода? (в описании указать время блокировки)", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Хранение пароля системы осуществляться в хэшированном виде?", answers = answersFactorsAuthQ2111)),
                questionRepo.insert(Question(name = "При хэшировании пароля используется соль?", answers = answersFactorsAuthQ2112)),
                questionRepo.insert(Question(name = "Реализовано хранение паролей в браузерах?", answers = answersFactorsAuthQ2114)),
                questionRepo.insert(Question(name = "Реализовано хранение паролей в нативных и гибридных мобильных приложениях?", answers = answersFactorsAuthQ2114)))

        //add questions
        var questionFactorsAuthI1: MutableList<Question> = mutableListOf(
                questionRepo.insert(Question(name = "Используются символы в верхнем регистре (A-Z или А-Я).", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Используются символы в нижнем регистре (a-z или а-я).", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Используются цифры (0-9).", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Используются специальные символы (например !, $, #, %).", answers = answersFactorsAuth)))

        //add questions
        var questionFactorsAuthI2: MutableList<Question> = mutableListOf(
                questionRepo.insert(Question(name = "Длина соли больше 15 символов?", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Соль содержит цифры (0-9)?", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Соль содержит символы в верхнем регистре (A-Z или А-Я)?", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Соль содержит символы в нижнем регистре (a-z или а-я)?", answers = answersFactorsAuth)),
                questionRepo.insert(Question(name = "Соль содержит специальные символы (например !, \$, #, %)?", answers = answersFactorsAuth)))

        //add inner part
        var partFactorsAuthI1: MutableList<PartQuestionnaire> = mutableListOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Предусмотрена возможность настройки различных сочетаний групп символов, используемых в пароле:",
                        parentId = "inner", questions = questionFactorsAuthI1)),
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "Если используется соль, то она удовлетворяет следующим условиям",
                        parentId = "inner", questions = questionFactorsAuthI2)))

        //add inner part
        var partFactorsAuth: MutableList<PartQuestionnaire> = mutableListOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "2.1 Логин и пароль",parentId = "inner", children = partFactorsAuthI1, questions = questionFactorsAuth)))

        //add external part
        var partList: MutableList<PartQuestionnaire> = mutableListOf(
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "1. Аутентификация", parentId = "list",questions = questionsAuth )),
                partQuestionnaireRepo.insert(PartQuestionnaire(name = "2. Факторы аутентификации", children = partFactorsAuth, parentId = "list")))


        //add lists
        var list1: MutableList<ListQuestionnaire> = mutableListOf(
                listQuestionnaireRepo.insert(ListQuestionnaire(listName = "Требования к аутентификации пользователей", parts = partList, domainSections = domain)))

        questionnaireRepository.insert(Questionnaire(name = "Шаблон опросника", lists = list1, creator = "admin"))
    }
}
