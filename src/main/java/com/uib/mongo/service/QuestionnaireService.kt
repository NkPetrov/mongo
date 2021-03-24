package com.uib.mongo.service

import com.uib.mongo.repository.ListQuestionnaireRepository
import com.uib.mongo.repository.QuestionnaireRepository
import com.uib.mongo.repository.entity.document.ListQuestionnaire
import com.uib.mongo.repository.entity.document.Questionnaire
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class QuestionnaireService(
        private val listQuestionnaireRepo: ListQuestionnaireRepository,
        private val questionnaireRepo: QuestionnaireRepository
) {
    fun getQuestionnaireByUser(user: User?): List<Questionnaire>? = questionnaireRepo.findByName(user?.username)

    fun getQuestionnaireByListIdAndQuestionnaireId(listId: BigInteger): ListQuestionnaire? =
            listQuestionnaireRepo.findByListId(listId)
}
