package com.uib.mongo.service

import com.uib.mongo.repository.DomainQuestionnaireRepository
import com.uib.mongo.repository.ListQuestionnaireRepository
import com.uib.mongo.repository.PartQuestionnaireRepository
import com.uib.mongo.repository.QuestionRepository
import com.uib.mongo.repository.QuestionnaireRepository
import com.uib.mongo.repository.entity.document.DomainQuestionnaire
import com.uib.mongo.repository.entity.document.ListQuestionnaire
import com.uib.mongo.repository.entity.document.PartQuestionnaire
import com.uib.mongo.repository.entity.document.Question
import com.uib.mongo.repository.entity.document.Questionnaire
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service

@Service
class QuestionnaireService(
        private val questionnaireRepo: QuestionnaireRepository,
        private val listQuestionnaireRepo: ListQuestionnaireRepository,
        private val domainQuestionnaireRepo: DomainQuestionnaireRepository,
        private val partQuestionnaireRepo: PartQuestionnaireRepository,
        private val questionRepo: QuestionRepository
        ) {
    fun getQuestionnaireByUser(user: User?): List<Questionnaire>? = questionnaireRepo.findByCreator(user?.username)

    fun getListQuestionnaireByListId(listId: String): ListQuestionnaire? =
            listQuestionnaireRepo.findByListId(listId)

//    fun getPartListQuestionnaireByPartListId(partListId: String): PartListQuestionnaire? =
//            partListQuestionnaireRepo.findByPartListId(partListId)

    fun getPartQuestionnaireByPartId(partId: String): PartQuestionnaire? =
            partQuestionnaireRepo.findByPartId(partId)

    fun getQuestionByQuestionId(questionId: String): Question? =
            questionRepo.findByQuestionId(questionId)

    fun getDomainById(domainId: String): DomainQuestionnaire? = domainQuestionnaireRepo.findByDomainId(domainId)

    fun saveEditList(list: ListQuestionnaire) = listQuestionnaireRepo.save(list)

//    fun saveEditPartList(part: PartListQuestionnaire) = partListQuestionnaireRepo.save(part)

    fun saveEditPart(part: PartQuestionnaire) = partQuestionnaireRepo.save(part)

    fun saveEditQuestion(question: Question) = questionRepo.save(question)

    fun saveEditDomain(domain: DomainQuestionnaire) = domainQuestionnaireRepo.save(domain)

    fun getPartRecursiveList(parts: List<PartQuestionnaire>): List<PartQuestionnaire>? {
        if (parts != null){
            return parts.flatMap { getPartRecursive(it) }
                    .toList()
                    .sortedBy { it.number }
        }else return null
    }

//    fun getPartRecursiveList(parts: List<PartQuestionnaire>?): List<PartQuestionnaire>? {
////        if (parts != null) {
////            for (part: PartQuestionnaire in parts) {
////                if (part.children != null) {
////                    return getPartRecursiveList(part.children)
////                }
////                return part.children
////            }
////        }
////        return null
////    }

    fun getPartRecursive(part: PartQuestionnaire): List<PartQuestionnaire> {
        return if (part.children != null)  {
            listOf(part) + part.children!!.flatMap { getPartRecursive(it) }
        } else {
            listOf(part)
        }
    }
}
